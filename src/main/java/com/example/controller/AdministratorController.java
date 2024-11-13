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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者情報登録画面を表示する処理.
 * 
 * @author M.aoki
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

  @Autowired
  private AdministratorService administratorService;

  @Autowired
  private HttpSession session;

  /**
   * 管理者情報登録画面を表示する.
   * 
   * @param form 画面入力情報
   * @return 管理者情報登録画面
   */
  @GetMapping("/toInsert")
  public String toInsert(
      InsertAdministratorForm form,
      Model model) {
    return "administrator/insert";
  }

  /**
   * 管理者情報を登録する.
   * 
   * @param form 画面入力情報
   * @return ログイン画面
   */
  @PostMapping("/insert")
  public String insert(
      @Validated InsertAdministratorForm form,
      BindingResult result,
      RedirectAttributes redirectAttributes,
      Model model) {

    if (result.hasErrors()) {
      return toInsert(form, model);
    }

    Administrator administrator = new Administrator();

    BeanUtils.copyProperties(form, administrator);

    administratorService.insert(administrator);

    return "redirect:/";
  }

  /**
   * ログイン画面を表示する.
   * 
   * @param form 画面入力情報
   * @return ログイン画面
   */
  @GetMapping("/")
  public String toLogin(LoginForm form) {
    return "administrator/login";
  }

  /**
   * ログイン処理をする.
   * 
   * @param form  画面入力情報
   * @param model リクエストスコープを使うためのオブジェクト
   * @return ログイン画面
   */
  @PostMapping("/login")
  public String login(LoginForm form, Model model) {

    Administrator administrator = new Administrator();
    administrator = administratorService.login(form.getMailAddress(), form.getPassword());

    if (administrator == null) {
      String errorMessage = "メールアドレスまたはパスワードが不正です。";

      model.addAttribute("errorMessage", errorMessage);

      return "administrator/login";
    }

    session.setAttribute("administratorName", administrator.getName());

    return "redirect:/employee/showList";
  }

  /**
   * ログアウトする.
   * 
   * @param form 画面入力情報
   * @return ログイン画面
   */
  @GetMapping("/logout")
  public String logout(LoginForm form) {
    session.invalidate();

    return "redirect:/";
  }
}
