package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.service.EmployeeService;

/**
 * @author harasawakana
 */

@Controller
@RequestMapping("/employee")

/**従業員情報を検索する処理 */
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/showList")
    public String showList(Model model) {
        model.addAttribute("employeeList", employeeService.showList());
        return "employee/list";
    }
}
