package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者登録画面を表示する処理を記述する
 *
 * @author T.Araki
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    /** セッションスコープ */
    @Autowired
    private HttpSession session;

    /** administoratorService */
    @Autowired
    private AdministratorService administratorService;

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

    /**
     * 管理者登録画面を表示する
     *
     * @param form フォーム
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
    public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return toInsert(form);
        }

        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        try {
            administratorService.insert(administrator);
        } catch (DuplicateKeyException e) {
            model.addAttribute("message", "このメールアドレスは既に登録されています");
            return toInsert(form);
        }

        return "redirect:/";
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

    /**
     * ログインをする処理
     *
     * @param form  ログインフォーム
     * @param model モデル
     * @return 従業員一覧画面(リダイレクト)
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        if (administrator == null) {
            model.addAttribute("message", true);
            return toLogin(form);
        }

        session.setAttribute("administratorName", administrator.getName());

        return "redirect:/employee/showList";
    }

    /**
     * ログアウト処理
     *
     * @param form ログインフォーム
     * @return ログイン画面
     */
    @GetMapping("/logout")
    public String logout(LoginForm form) {
        session.invalidate();
        return "redirect:/";
    }

}
