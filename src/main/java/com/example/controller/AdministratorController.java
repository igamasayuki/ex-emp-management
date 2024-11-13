package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Author:金丸天
 *             dministratorのコントローラークラスです
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    /**
     * フォームオブジェクトをスコープに格納
     * 
     * @return LoginFormオブジェクト
     */
    @ModelAttribute
    public LoginForm loginForm() {
        return new LoginForm();
    }

    @Autowired
    private HttpSession session;

    @Autowired
    private AdministratorService administratorService;

    /**
     * @param form
     * @return administrator/insertフォワード
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * サービスクラスのinsertメソッドを使って登録処理を行う
     * 
     * @param form 登録処理を行うフォーム
     * @return redirect:/
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();

        BeanUtils.copyProperties(form, administrator);

        administratorService.insert(administrator);

        return "redirect:/";

    }

    /**
     * ログイン画面への遷移/ログイン失敗時の遷移
     * 
     * @param form  入力された情報
     * @param model 受け取ったフォーム情報を画面に渡す
     * @return
     */
    @GetMapping("/")
    public String toLogin(LoginForm form, Model model) {
        return "administrator/login";
    }

    /**
     * ログイン成功/失敗の判断
     * 
     * @param form
     * @param result
     * @param model
     * @return エラーが発生したらログイン画面に/成功したらemployee/showListにリダイレクト
     */
    @PostMapping("/login")
    public String login(@Validated LoginForm form, BindingResult result, Model model) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "メールアドレスまたがパスワードが不正です。");
            return toLogin(form, model);
        }

        if (administrator == null) {
            model.addAttribute("errorMessage", "メールアドレスまたがパスワードが不正です。");
            return "administrator/login";
        }

        session.setAttribute("administratorName", administrator.getName());

        return "redirect:/employee/showList";

    }

    /**
     * ログアウト処理
     * 
     * @param form 登録に必要なリクエストパラメータを受け取る属性
     * @return administrator/loginにリダイレクト
     */
    @GetMapping("/logout")
    public String logout(LoginForm form) {

        session.invalidate();
        return "administrator/login";
    }

}
