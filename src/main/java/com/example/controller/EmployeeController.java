package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

/**
 * 従業員情報を検索する処理を記述する.
 * 
 * @author M.aoki
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  /**
   * 従業員情報を全件取得する.
   * 
   * @param model リクエストスコープを使うためのオブジェクト
   * @return 従業員一覧画面
   */
  @GetMapping("/showList")
  public String showList(Model model) {
    List<Employee> employeeList = new ArrayList<>();
    employeeList = employeeService.showList();

    model.addAttribute("employeeList", employeeList);

    return "employee/list";
  }

  /**
   * 従業員情報を取得する.
   * 
   * @param id    従業員ID
   * @param model リクエストスコープを使うためのオブジェクト
   * @param form  画面入力情報
   * @return 従業員詳細情報
   */
  @GetMapping("/showDetail")
  public String showDetail(
      String id,
      Model model,
      UpdateEmployeeForm form) {

    int employeeId = Integer.parseInt(id);

    Employee employee = new Employee();
    employee = employeeService.showDetail(employeeId);

    model.addAttribute("employee", employee);

    return "employee/detail";
  }

  /**
   * 従業員詳細情報を更新する.
   * 
   * @param form 画面入力情報
   * @return 従業員一覧画面
   */
  @PostMapping("/update")
  public String update(
      @Validated UpdateEmployeeForm form,
      BindingResult result,
      RedirectAttributes redirectAttributes,
      Model model) {

    if (result.hasErrors()) {
      return showDetail(form.getId(), model, form);
    }

    int id = Integer.parseInt(form.getId());

    Employee employee = new Employee();
    employee = employeeService.showDetail(id);

    int inputDependentsCount = Integer.parseInt(form.getDependentsCount());
    employee.setDependentsCount(inputDependentsCount);

    employeeService.update(employee);

    return "redirect:/employee/showList";
  }
}
