package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;

    @GetMapping
    public String toLogin(LoginForm loginForm, Model model) {
        model.addAttribute("loginForm", loginForm);
        return "administrator/login";
    }

    /**
     * 管理者情報をメールアドレスとパスワードで検索する
     * @param insertAdministratorForm 登録する管理者情報
     * @param model リクエストスコープ
     * @return リダイレクト先のURL
     * @author kazuma.saito
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm, Model model) {
        model.addAttribute("administratorForm", insertAdministratorForm);
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する
     * @param insertAdministratorForm 登録する管理者情報
     * @param model リクエストスコープ
     * @return リダイレクト先のURL
     * @author kazuma.saito
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm insertAdministratorForm, Model model) {
        Administrator administrator = new Administrator();
        administrator.setName(insertAdministratorForm.getName());
        administrator.setMailAddress(insertAdministratorForm.getMailAddress());
        administrator.setPassword(insertAdministratorForm.getPassword());

        administratorService.insert(administrator);
        return "redirect:/";
    }
}
