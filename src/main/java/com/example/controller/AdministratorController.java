package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

/**
 * 管理者登録画面を表示する処理を記述する
 *
 * @author T.Araki
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    /**
     * InsertAdministratorFormオブジェクトを生成
     *
     * @return InsertAdministratorFormオブジェクト
     */
    @ModelAttribute
    private InsertAdministratorForm setUpInsertAdministratorForm() {
        return new InsertAdministratorForm();
    }

    /** administoratorService */
    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }
}
