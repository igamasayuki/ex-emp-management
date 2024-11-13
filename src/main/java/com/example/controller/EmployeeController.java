package com.example.controller;

import java.sql.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    /** employeeService */
    @Autowired
    private EmployeeService employeeService;

    /**
     * EmployeeFormオブジェクトの作成
     *
     * @return EmployeeFormオブジェクト
     */
    @ModelAttribute
    private UpdateEmployeeForm setUpUpdateEmployeeForm() {
        return new UpdateEmployeeForm();
    }

    /**
     * 従業員一覧を出力する
     *
     * @param model モデル
     * @return 従業員一覧画面
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        model.addAttribute("employeeList", employeeService.showList());
        return "employee/list";
    }

    /**
     * 従業員詳細を出力する
     *
     * @param model モデル
     * @param form  従業員更新フォーム
     * @return 従業員詳細画面
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form) {
        model.addAttribute("employee", employeeService.showDetail(Integer.parseInt(id)));
        return "employee/detail";
    }

    /**
     * 従業員詳細を更新する
     *
     * @param form 従業員更新フォーム
     * @return 従業員一覧画面(リダイレクト)
     */
    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return showDetail(form.getId(), model, form);
        }

        Employee employee = new Employee();
        BeanUtils.copyProperties(form, employee);
        employee.setId(Integer.parseInt(form.getId()));
        employee.setImage(form.getImage());
        employee.setHireDate(Date.valueOf(form.getHireDate()));
        employee.setSalary(Integer.parseInt(form.getSalary()));
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));

        employeeService.update(employee);
        return "redirect:/employee/showList";
    }

}
