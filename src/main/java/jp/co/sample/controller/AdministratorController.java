package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	AdministratorService administratorService;
	
	/**従業員登録する際のリクエストパラメータが格納される 
	 * InsertAdministratorForm オブジェクトが
	 *  Model オブジェクト(リクエストスコープ)に⾃動的に格納される*/
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return insertAdministratorForm;
	}
	
	/**「administrator/insert.html」にフォワードする*/
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}

}
