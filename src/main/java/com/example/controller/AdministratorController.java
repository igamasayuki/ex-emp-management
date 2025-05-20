package com.example.controller;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm, Model model) {
        model.addAttribute("administratorForm", insertAdministratorForm);
        return "administrator/insert";
    }
}
