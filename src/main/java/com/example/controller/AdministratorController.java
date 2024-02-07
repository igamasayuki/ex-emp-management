package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    /**
     * InsertAdministratorFormを引数で受け取り,
     * administrator/insert.htmlへフォワード.
     * 
     * @param form
     * @return
     */
    @GetMapping({ "/toInsert", "/toInsert/" })
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";

    }
}
