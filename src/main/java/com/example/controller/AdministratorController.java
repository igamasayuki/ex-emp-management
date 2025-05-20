package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession session;

    /**
     * ログイン画面に遷移する
     * @param loginForm ログインフォーム
     * @param model リクエストスコープ
     * @return ログイン画面のURL
     */
    @GetMapping
    public String toLogin(LoginForm loginForm, Model model) {
        model.addAttribute("loginForm", loginForm);
        return "administrator/login";
    }

    /**
     * 管理者情報をメールアドレスとパスワードで検索する
     * @param loginForm ログインフォーム
     * @param model リクエストスコープ
     * @return リダイレクト先のURL
     */
    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model) {
        Administrator administrator = administratorService.login(loginForm.getMailAddress(), loginForm.getPassword());
        if (administrator == null) {
            model.addAttribute("loginError", "メールアドレスまたはパスワードが不正です。");
            return toLogin(loginForm, model);
        }
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }

    /**
     * 管理者情報登録画面に遷移する
     * @param insertAdministratorForm 登録する管理者情報
     * @param model リクエストスコープ
     * @return 管理者情報登録画面のURL
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
     * @return ログイン画面のURL
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
