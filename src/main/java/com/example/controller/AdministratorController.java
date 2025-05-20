package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者用Controller.
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;
    private final HttpSession session;

    /**
     * ログイン画面に遷移する.
     *
     * @param loginForm ログインフォーム
     * @return ログイン画面
     */
    @GetMapping
    public String toLogin(LoginForm loginForm) {
        return "administrator/login";
    }

    /**
     * 管理者情報をメールアドレスとパスワードで検索する.
     *
     * @param loginForm ログインフォーム
     * @param model リクエストスコープ
     * @return リダイレクト先のURL
     */
    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model) {
        Administrator administrator = administratorService.login(loginForm.getMailAddress(), loginForm.getPassword());
        if (administrator == null) {
            model.addAttribute("loginError", "メールアドレスまたはパスワードが不正です。");
            return toLogin(loginForm);
        }
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }

    /**
     * 管理者情報登録画面に遷移する.
     *
     * @param insertAdministratorForm 登録する管理者情報
     * @return 管理者情報登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する.
     *
     * @param insertAdministratorForm 登録する管理者情報
     * @return ログイン画面
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm insertAdministratorForm) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(insertAdministratorForm, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }
}
