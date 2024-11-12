package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

/**
 * @author 金丸天
 *         employeesのコントロールクラス
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    /**
     * employeeServiceクラスのオブジェクトを注入
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * EmployeeServiceクラスのshowListを呼び出し
     * "employees/list"にフォワードする
     * 
     * @param model
     * @return
     */

    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

}
