package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.repository.AdministratorRepository;
import com.example.service.AdministratorService;

//@Author:金丸天
//Administratorのコントローラークラスです
@Controller
@RequestMapping("/")
public class AdministratorController {

    // administratorServiceをオブジェクトの注入
    @Autowired
    private AdministratorService administratorService;

    // administrator/insertフォワード
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

}
