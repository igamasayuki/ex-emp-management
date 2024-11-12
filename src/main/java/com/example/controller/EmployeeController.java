package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
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

    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
        Integer emID = Integer.parseInt(id);
        Employee employee = employeeService.showDetail(emID);
        model.addAttribute("employee", employee);

        return "employee/detail.html";

    }

    @PostMapping("/update")
    public String update(UpdateEmployeeForm form) {
        Integer EmId = Integer.parseInt(form.getId());
        Employee employee = employeeService.showDetail(EmId);
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
        employeeService.update(employee);
        return "redirect:/employee/showList";

    }

}
