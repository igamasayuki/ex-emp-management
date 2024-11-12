package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form, Model model) {
        form.setName(form.getName());
        form.setMailAddress(form.getMailAddress());
        form.setPassword(form.getPassword());
        model.addAttribute("form", form);
        return "administrator/insert";
    }

    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());
        administratorService.insert(administrator);

        return "redirect:/";

    }

    // Loginformからリクエストパラメータを受け取る
    @GetMapping("/")
    public String tologin(LoginForm form, Model model) {
        model.addAttribute("form", form);
        return "administrator/login.html";
    }

    @PostMapping("/login")
    public String login(LoginForm form) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        // ログイン失敗時の処理
        if (administrator == null) {
            // エラーメッセージをセッションに一時的に格納
            session.setAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
            return "redirect:/";
        }

        // ログイン成功時の処理
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }

}
