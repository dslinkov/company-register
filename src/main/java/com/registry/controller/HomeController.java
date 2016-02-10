package com.registry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping({ "/{[path:[^\\.]*}", "/companies", "/home", "/company/{companyId}"})
    public String redirect() {
        return "forward:/";
    }

}