package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 従業員操作用のコントローラ.
 */
@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * 従業員一覧を取得し、従業員一覧画面を表示する.
     *
     * @param model リクエストスコープ
     * @return 従業員一覧画面
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        // 従業員一覧を取得
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }

    /**
     * 従業員詳細を取得し、従業員詳細画面を表示する.
     *
     * @param id    従業員ID
     * @return 従業員詳細画面
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm updateEmployeeForm) {
        Employee employee = employeeService.showDetail(Integer.parseInt(id));
        model.addAttribute("employee", employee);
        return "employee/detail";
    }
}
