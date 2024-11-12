package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者テーブルに対応するコントローラクラス
 * 必要な情報をフォームから受け取り、サービスを通じて処理を行う
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    /**
     * 管理者登録画面にフォワードする
     * @param form
     * @return 管理者登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert.html";
    }

    /**
     * フォームから管理者情報を追加する
     * @param form
     * @return　ログイン画面
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        service.insert(administrator);
        return "/";

    }

    /**
     * ログイン画面にフォワードする
     * @param form
     * @return ログイン画面
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String login(
    @Validated LoginForm form
    ,BindingResult result
    ,HttpSession administratorName
    ,Model model) {
        if(result.hasErrors()) {
            return "administrator/login";
        }

        Administrator administrator = service.login(form.getMailAddress(), form.getPassword());
        if(administrator == null) {
            model.addAttribute("error", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }else {
            administratorName.setAttribute("name", administrator.getName());
            return "redirect:/employee/showList";
        }
    }

    @RequestMapping("/employee/showList")
    public String showList() {
        return "employee/list";
    }

}
