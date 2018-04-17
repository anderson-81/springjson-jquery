package com.springjson.controllers;

import com.google.gson.Gson;
import com.springjson.models.UserSys;
import com.springjson.services.daos.UserDAO;
import com.springjson.services.functions.Alert;
import com.springjson.services.functions.ValidationData;
import com.springjson.services.security.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Alert alert;

    @Autowired
    private ValidationData validationData;

    @Autowired
    private UserSys usersys;

    @Autowired
    private Token tokenobj;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String New(HttpSession session) {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String Authenticate(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpSession session, @RequestParam("token") String token) {
        Gson gson = new Gson();
        Map<String, Object> datas = new HashMap<>();
        if (session.getAttribute("token").toString().equals(token)) {
            try {
                List<String> errors = validationData.ValidationUser(username, password);
                if (errors.isEmpty()) {
                    UserSys usersys_return = userDAO.Login(usersys.GetUserSys(username, password));
                    if (usersys_return != null) {
                        session.setAttribute("name", usersys_return.getName());
                        datas.put("alert", alert.GetAlert(4, 1));
                        datas.put("css", "success");
                        datas.put("page", "index");
                    } else {
                        datas.put("alert", alert.GetAlert(3, 3));
                        datas.put("css", "danger");
                    }
                } else {
                    datas.put("errors", errors);
                }
            } catch (Exception e) {
                datas.put("page", "errors/505");
            }
        } else {
            datas.put("page", "errors/505");
        }
        return gson.toJson(datas);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody
    String Logout(HttpSession session) {
        Gson gson = new Gson();
        Map<String, Object> datas = new HashMap<>();
        try {
            session.invalidate();
            datas.put("alert", alert.GetAlert(5, 1));
            datas.put("page", "index");
        } catch (Exception e) {
            datas.put("page", "errors/505");
        }
        return gson.toJson(datas);
    }
}
