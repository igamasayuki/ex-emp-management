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
import com.example.form.LoginForm;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

/**
 * 従業員関連画面を表示する処理を記述する
 *
 * @author T.Araki
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    /** セッションスコープ */
    @Autowired
    private HttpSession session;

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
     * LoginFormオブジェクトの作成
     *
     * @return LoginFormオブジェクト
     */
    @ModelAttribute
    private LoginForm setUpUpdateLoginForm() {
        return new LoginForm();
    }

    /**
     * 従業員一覧を出力する
     *
     * @param model モデル
     * @return 従業員一覧画面
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        if (session.getAttribute("administratorName") == null) {
            return "forward:/";
        }
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
        if (session.getAttribute("administratorName") == null) {
            return "forward:/";
        }
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
        if (session.getAttribute("administratorName") == null) {
            return "forward:/";
        }
        if (result.hasErrors()) {
            return showDetail(form.getId(), model, form);
        }

        Employee employee = employeeService.showDetail(Integer.parseInt(form.getId()));
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
        employeeService.update(employee);
        return "redirect:/employee/showList";
    }

}
