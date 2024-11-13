package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
     * @param model リクエストスコープ
     * @return "employee/list"に画面遷移
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    /**
     * EmployeeServiceクラスのshowDetailを呼び出し
     * "employees/detail.html"にフォワードする
     * 
     * @param id    employeeのID
     * @param model リクエストスコープ
     * @return employee/detail.html
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
        Integer emID = Integer.parseInt(id);
        Employee employee = employeeService.showDetail(emID);
        model.addAttribute("employee", employee);

        return "employee/detail.html";

    }

    /**
     * "employees/update.html"にフォワードする
     * 
     * @param id     employeeのID
     * @param result エラー内容の格納
     * @param model  リクエストスコープ
     * @return employee/update.html
     */
    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(form);
            System.out.println(result.hasErrors());
            System.out.println(result);
            return showDetail(form.getId(), model, form);

        }

        Integer EmId = Integer.parseInt(form.getId());
        Employee employee = employeeService.showDetail(EmId);
        form.getId();
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
        BeanUtils.copyProperties(form, employee);

        employeeService.update(employee);
        return "redirect:/employee/showList";

    }

}
