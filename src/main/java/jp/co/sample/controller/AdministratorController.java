package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * 
	 * @return InsertAdministratorForm オブジェクトが Model オブジェクト(リクエストスコープ)に自動的に格納
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return insertAdministratorForm;
	}
	/**
	 * 
	 * @return 「administrator/insert.html」にフォワード
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		//InsertAdministratorForm オブジェクトの中身を今インスタンス化したAdministratorドメインオブジェクトにコピーする
		BeanUtils.copyProperties(form, administrator);
		//administratorService の insert()メソッドを呼ぶ
		administratorService.insert(administrator);
		//ログイン画面にリダイレクト
		return "redirect:/";
	}

}
