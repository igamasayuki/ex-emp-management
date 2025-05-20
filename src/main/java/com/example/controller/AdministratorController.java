package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 管理者情報を操作するコントローラー
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    /**
     * 管理者登録画面を出力します.
     *
     * @param form　フォーム
     * @return 管理者画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }

    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){

        Administrator administrator = new Administrator();

//        手動で設定
//        administrator.setName(form.getName());
//        administrator.setPassword(form.getPassword());
//        administrator.setMailAddress(form.getMailAddress());

        //自動で設定
        BeanUtils.copyProperties(form, administrator);

        administratorService.insert(administrator);
        return "redirect:/";

    }


}
