package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
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

    /**
     * LoginFormオブジェクトを生成
     *
     * @return LoginFormオブジェクト
     */
    @ModelAttribute
    private LoginForm setUpLoginForm() {
        return new LoginForm();
    }

    /** administoratorService */
    @Autowired
    private AdministratorService administratorService;

    /**
     * 管理者登録画面を表示する
     *
     * @return 管理者登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する
     *
     * @param form InsertAdministratorForm(管理者情報入力値)
     * @return ログイン画面(リダイレクト)
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redierct:/";
    }

    /**
     * ログイン画面を表示する
     *
     * @param form ログインフォーム
     * @return ログイン画面
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {

        return "administrator/login";
    }

}
