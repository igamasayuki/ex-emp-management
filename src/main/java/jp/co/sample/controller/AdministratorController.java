package jp.co.sample.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	public String insert(@Validated InsertAdministratorForm form,BindingResult result) {
		if(result.hasErrors()) {
			return "administrator/insert";
		}
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
	public String login(@Validated LoginForm form,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "administrator/login";
		}
		Administrator administrator=new Administrator();
		String mailAddress=form.getMailAddress();
		String password=form.getPassword();
		administrator=administratorService.login(mailAddress,password);
		if(Objects.equals(administrator,null)) {
			model.addAttribute("message","メールアドレスまたはパスワードが不正です。");
			return "forward:/";
		}else {
			session.setAttribute("administrator",administrator.getName());
			return "forward:/employee/showList";
		}
	}
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}