package jp.co.sample.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員情報を取得
	 * @param model
	 * @return list.htmlへフォワード
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> allList=employeeService.showList();
		model.addAttribute("employeeList",allList);
		return "employee/list.html";
	}
	
	/**
	 * idより従業員情報を取得
	 * @param id
	 * @param model
	 * @return detail.htmlへフォワード
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		Employee employee=employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee",employee);
		return "employee/detail.html";
	}
}