package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  /**
   * 
   * @param model
   * @return 従業員一覧画面
   */
  @GetMapping("/showList")
  public String showList(Model model) {
    List<Employee> employeeList = new ArrayList<>();
    employeeList = employeeService.showList();

    model.addAttribute("employeeList", employeeList);

    return "employee/list";
  }

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
}
