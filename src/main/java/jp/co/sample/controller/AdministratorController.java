package jp.co.sample.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * @return insert.htmlへフォワード
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	/**
	 * インスタンス化したadministratorオブジェクトにリクエストパラメータが入ったFormオブジェクトの内容をコピー
	 * @param form
	 * @return ログイン画面へリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator=new Administrator();
		BeanUtils.copyProperties(form,administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	/**
	 * @return login.htmlへフォワード
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}
	
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		Administrator administrator=new Administrator();
		String mailAddress=form.getMailAddress();
		String password=form.getPassword();
		administrator=administratorService.login(mailAddress,password);
		System.out.println(administrator);
		if(Objects.equals(administrator,null)) {
			model.addAttribute("message","メールアドレスまたはパスワードが不正です。");
			return "forward:/";
		}else {
			session.setAttribute("administrator",administrator.getName());
			return "forward:/employee/showList";
		}
	}
}