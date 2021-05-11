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

	@RequestMapping("/showList")
	public String showList(Model model) {
		System.out.println("aaa");
		// employeeServiceのshowList()メソッドを呼び出し従業員⼀覧(List)を取得する。
		List<Employee> employee = employeeService.showList();

		// 次の画面に表示するためにrequestスコープに取得した従業員⼀覧を
		// 「employeeList」という名前を付けて格納する。
		model.addAttribute("employeeList", employee);

		// 「employee/list.html」にフォワードする。
		return "employee/list";
	}
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm(){
		//UpdateEmployeeFormをインスタンス化しそのままreturn
		UpdateEmployeeForm updateEmployeeForm = new UpdateEmployeeForm();
		return updateEmployeeForm;
		
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		//リクエストパラメータで送られてくる従業員IDを引数に(int型に変換してから)渡し
		//model.addAttribute(Integer.parseInt("id"));	
		
		//employeeServiceのshowDetail()メソッドを呼ぶ 
		//従業員情報(Employee)が戻り値として返ってくるのでそれを受け取る
		Employee employee  = employeeService.showDetail(Integer.parseInt(id));
		
		//次の画面に表示するためにrequestスコープに従業員情報を「employee」という名前を付けて格納
		model.addAttribute("employee",employee);
		
		//・「employee/detail.html」にフォワード
		return "employee/detail";
	}
	
}
