package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    /**
     * 管理者登録画面を表示する
     * @param insertAdministratorForm
     * @return 管理者登録画面へフォワードする
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm) {
        
        return "administrator/insert";
    }

    /**
     * 管理者情報登録処理
     * @param insertAdministratorForm 登録する管理者情報
     * @return ログイン画面へリダイレクトする
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm insertAdministratorForm) {
        // Administratorエンティティをインスタンス化
        Administrator administrator = new Administrator();
        // 受け取ったフォームパラメータをエンティティへコピーする
        BeanUtils.copyProperties(insertAdministratorForm, administrator);

        // 登録処理
        administratorService.insert(administrator);

        // ログイン画面へリダイレクトする
        return "redirect:administrator/login";
    }
}
