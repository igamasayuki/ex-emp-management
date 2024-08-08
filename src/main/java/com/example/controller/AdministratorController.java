package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;


/**
 * 管理者登録画面を表示するコントローラクラス
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;
    /**
     * 管理者情報登録画面に遷移するメソッド
     * @param form 登録フォームオブジェクト
     * @param model モデルオブジェクト
     * @return フォワード先のテンプレート名
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form, Model model) {
        model.addAttribute("insertAdministratorForm", form);
        return "administrator/insert";
    }

    /**
     * ログイン画面に遷移するメソッド
     * @param form　ログインフォームオブジェクト
     * @param model モデルオブジェクト
     * @return フォワード先のテンプレート名
     */
    @GetMapping("/")
    public String toLogin(LoginForm form, Model model){
        model.addAttribute("loginForm", form);          
        return "administrator/login";
    }
    
}
