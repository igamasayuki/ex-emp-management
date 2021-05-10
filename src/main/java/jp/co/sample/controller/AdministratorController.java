package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm(){
		return new InsertAdministratorForm();
	}
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form,RedirectAttributes redirectAttributes) {
		 //Administratorドメインをインスタンス化する
		 Administrator administrator = new Administrator();
		 
		 //リクエストパラメータが入っているInsertAdministratorFormオブジェクトの
		 //中身を今インスタンス化したAdministratorドメインオブジェクトにコピーする
		 BeanUtils.copyProperties(form, administrator);
		 administratorService.insert(administrator);
		 
		 //(ログイン画面)にリダイレクトする
		 return "redirect:/administrator/insert";
	}
}


