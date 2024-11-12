package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

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

  /**
   * 管理者情報登録画面を表示する.
   * 
   * @param form
   * @return 管理者情報登録画面
   */
  @GetMapping("/toInsert")
  public String toInsert(InsertAdministratorForm form) {
    return "administrator/insert";
  }
}
