package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧を表示します。
     *
     * @param model リクエストスコープに格納
     * @return 従業員一覧を表示するビュー名
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employees = employeeService.showList(); 
        model.addAttribute("employees", employees);
        return "employee/list";
    }

}
