package com.jy.hello.spring.boot.thymeleaf.controller;

import com.jy.hello.spring.boot.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public  String index(Model model){
        User user  = new User();
        user.setName("贾云66666");
        model.addAttribute("user",user);
        return "index";
    }
}
