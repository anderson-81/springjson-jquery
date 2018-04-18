package com.springjson.controllers;

import com.google.gson.Gson;
import com.springjson.models.Person;
import com.springjson.services.daos.PersonDAO;
import com.springjson.services.functions.Alert;
import com.springjson.services.functions.FormatDateFunction;
import com.springjson.services.functions.Picture;
import com.springjson.services.functions.ValidationData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "/person")
public class PersonController {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private Alert alert;

    @Autowired
    private Picture picture;

    @Autowired
    private ValidationData validationData;

    @Autowired
    private Person person;

    @Autowired
    private FormatDateFunction dateFormat;

    @RequestMapping(value = "/person/index", method = RequestMethod.GET)
    public String Index() {
        return "person/index";
    }

    @RequestMapping(value = "/person/list", method = RequestMethod.PUT)
    public @ResponseBody
    String List() {
        Gson gson = new Gson();
        try {
            List<Person> posts = personDAO.GetPersonsByName("");
            return gson.toJson(posts);
        } catch (Exception e) {
            return "errors/505";
        }
    }

    @RequestMapping(value = "/person/new", method = RequestMethod.GET)
    public String New(HttpSession session) {
        return "person/new";
    }

    @RequestMapping(value = "/person/edit/{id}", method = RequestMethod.GET)
    public String Edit(@PathVariable("id") int id, HttpSession session) {
        return "person/edit";
    }

    @RequestMapping(value = "/person/edit/{id}/{token}", method = RequestMethod.GET)
    public @ResponseBody
    String GetPersonByID(@PathVariable("id") int id, @PathVariable("token") String token, HttpSession session) {
        Gson gson = new Gson();
        Map<String, Object> datas = new HashMap<>();
        if (session.getAttribute("token").toString().equals(token)) {
            try {
                person = personDAO.GetPersonByID(id);
                datas.put("person", person);
                datas.put("picture", picture.ByteToBase64(person.getPicture()));
                datas.put("birthday", dateFormat.FormatDate(person.getBirthday()));
                return gson.toJson(datas);
            } catch (Exception e) {
                return "errors/505";
            }
        }
        return "errors/505";
    }

    @ValidateOnExecution(type = ExecutableType.NONE)
    @RequestMapping(value = "/person/new", method = RequestMethod.POST)
    public @ResponseBody
    String Save(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthday") String birthday, @RequestParam(value = "salary") String salary, @RequestParam(value = "gender") String gender, HttpSession session, @RequestParam(value = "token") String token, @RequestParam(value = "picture", required = false) CommonsMultipartFile file) {
        Gson gson = new Gson();
        Map<String, Object> datas = new HashMap<>();
        byte[] picture = null;
        if (session.getAttribute("token").toString().equals(token)) {
            try {
                if (file != null) {
                    if (file.getBytes().length > 0) {
                        picture = file.getBytes();
                    }
                }
            } catch (Exception e) {
                datas.put("page", "/errors/505");
                return gson.toJson(datas);
            }
            List<String> errors = validationData.ValidationPerson(name, email, birthday, salary, gender, null);
            if (errors.isEmpty()) {
                int result = personDAO.InsertPerson(person.GetPerson(name, email, birthday, salary, gender, picture));
                if (result != -1) {
                    datas.put("alert", alert.GetAlert(1, result));
                    datas.put("page", "/person/index");
                }
                if (result == -1) {
                    datas.put("page", "/errors/505");
                }
            } else {
                datas.put("errors", errors);
                datas.put("person", person);
            }
        } else {
            datas.put("page", "/errors/505");
        }

        return gson.toJson(datas);
    }

    @ValidateOnExecution(type = ExecutableType.NONE)
    @RequestMapping(value = "/person/edit/update", method = RequestMethod.POST)
    public @ResponseBody
    String Update(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "birthday") String birthday, @RequestParam(value = "salary") String salary, @RequestParam(value = "gender") String gender, HttpSession session, @RequestParam("token") String token, @RequestParam(value = "picture", required = false) CommonsMultipartFile file) {
        Gson gson = new Gson();
        Map<String, Object> datas = new HashMap<>();
        if (session.getAttribute("token").toString().equals(token)) {
            try {
                person = personDAO.GetPersonByID(id);
                if (person != null) {
                    if (!"".equals(person.getName())) {
                        List<String> errors = null;

                        if (!email.equals(person.getEmail())) {
                            errors = validationData.ValidationPerson(name, email, birthday, salary, gender, null);
                        } else {
                            errors = validationData.ValidationPerson(name, null, birthday, salary, gender, null);
                        }
                        if (errors.isEmpty()) {
                            int result = personDAO.EditPerson(person.GetPerson(name, email, birthday, salary, gender, file.getBytes()));
                            if (result != -1) {
                                datas.put("alert", alert.GetAlert(2, result));
                                datas.put("page", "/person/index");
                            }
                            if (result == -1) {
                                datas.put("page", "/errors/505");
                            }

                        } else {
                            datas.put("errors", errors);
                            datas.put("person", person);
                        }
                    } else {
                        datas.put("page", "/errors/404");
                    }

                } else {
                    datas.put("page", "/errors/505");
                }
            } catch (NumberFormatException e) {
                datas.put("page", "/errors/505");
            }
        } else {
            datas.put("page", "/errors/505");
        }
        return gson.toJson(datas);
    }

    @RequestMapping(value = "/person/delete/{id}/{token}", method = RequestMethod.GET)
    public @ResponseBody
    String Delete(@PathVariable("id") int id, @PathVariable("token") String token, HttpSession session) {
        Gson gson = new Gson();
        Map<String, Object> datas = new HashMap<>();
        if (session.getAttribute("token").toString().equals(token)) {
            try {
                person = personDAO.GetPersonByID(id);
                if (person != null) {
                    if (!"".equals(person.getName())) {
                        int result = personDAO.DeletePerson(person);
                        if (result != -1) {
                            datas.put("alert", alert.GetAlert(3, result));
                            datas.put("page", "/person/index");
                        }
                        if (result == -1) {
                            datas.put("page", "errors/505");
                        }
                    } else {
                        datas.put("page", "/errors/404");
                    }
                } else {
                    datas.put("page", "/errors/505");
                }
            } catch (NumberFormatException e) {
                datas.put("page", "/errors/505");
            }
        } else {
            datas.put("page", "/errors/505");
        }
        return gson.toJson(datas);
    }
}
