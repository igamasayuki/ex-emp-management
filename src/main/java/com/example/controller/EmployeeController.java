package com.example.controller;

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

        Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
        employeeService.update(employee);
        return "redirect:/employee/showList";
    }

    /**
     * 従業員情報を入社日の名前と期間で検索
     *
     * @param name    名前
     * @param started 入社日(開始期間)
     * @param ended   入社日(終了期間)
     * @return 従業員情報リスト
     */
    @PostMapping("/search")
    public String search(String name, String started, String ended, Model model) {
        model.addAttribute("employeeList", employeeService.serach(name, started, ended));
        return "employee/list";
    }

}
