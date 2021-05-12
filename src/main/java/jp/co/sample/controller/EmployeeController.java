package jp.co.sample.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
		System.out.println(employee);
		return "employee/detail.html";
	}
	
	/**
	 * 扶養情報を更新
	 * @param form
	 * @return /employee/showListにリダイレクト
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		System.out.println(form.getId());
		Employee employee=employeeService.showDetail(Integer.parseInt(form.getId()));
		employee.setName(form.getName());
		employee.setGender(form.getGender());
		employee.setHireDate(Date.valueOf(form.getHireDate()));
		employee.setMailAddress(form.getMailAddress());
		employee.setZipCode(form.getZipCode());
		employee.setAddress(form.getAddress());
		employee.setTelephone(form.getTelephone());
		employee.setSalary(Integer.parseInt(form.getSalary()));
		employee.setCharacteristics(form.getCharacteristics());
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		System.out.println(form);
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
}