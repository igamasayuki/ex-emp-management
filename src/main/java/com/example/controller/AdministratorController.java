package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
    
    @GetMapping({"","/"})
    public String index() {
        System.out.println("test9");
        return "administrator/insert";
    }
}
