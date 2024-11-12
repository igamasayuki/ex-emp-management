package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
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

    /**
     * サービスクラスのinsertメソッドを使って登録処理を行う
     * 
     * @param form
     * @return
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();

        BeanUtils.copyProperties(form, administrator);

        administratorService.insert(administrator);

        return "redirect://toInsert";

    }

}
