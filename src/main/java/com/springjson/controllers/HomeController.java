package com.springjson.controllers;

import com.google.gson.Gson;
import com.springjson.services.functions.Alert;
import com.springjson.services.security.Token;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private Token tokenobj;
    
    @Autowired
    private Alert alert;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index(HttpSession session) {
        return "home/index";
    }
    
    @RequestMapping(value = "/errors/404", method = RequestMethod.GET)
    public String Show404(HttpSession session) {
        return "errors/404";
    }
    
    @RequestMapping(value = "/errors/505", method = RequestMethod.GET)
    public String Show505(HttpSession session) {
        return "errors/505";
    }
      
    @RequestMapping(value = "/token", method = RequestMethod.PUT)
    public @ResponseBody
    String token(HttpSession session) {
        Gson gson = new Gson();
        String token = tokenobj.GenerateToken();
        session.setAttribute("token", token);
        return gson.toJson(token);
    }
    
    @RequestMapping(value = "/getsession", method = RequestMethod.GET)
    public @ResponseBody
    String GetSession(HttpSession session) {
        Gson gson = new Gson();
        try {
            return gson.toJson(session.getAttribute("name"));
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
}
