package jp.co.sample.controller;

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
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private HttpSession session;

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
	public String insert(@Validated InsertAdministratorForm form, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "administrator/insert";
		}
		Administrator administrator = new Administrator();
		//InsertAdministratorForm オブジェクトの中身を今インスタンス化したAdministratorドメインオブジェクトにコピーする
		BeanUtils.copyProperties(form, administrator);
		//administratorService の insert()メソッドを呼ぶ
		administratorService.insert(administrator);
		//ログイン画面にリダイレクト
		return "redirect:/";
	}
	/**
	 * ログインする際のリクエストパラメータが格納される
	 * LoginFormオブジェクトがModelオブジェクト(リクエストスコープ)に自動的に格納
	 * @return
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm(){
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}
	//ログイン画面にフォワード
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		//戻り値が null だったらログイン失敗のため「メールアドレスまたはパスワードが不正です。」
		//というエラーメッセージを Model オブジェクト(リクエストスコープ)にセットする。
		if(administratorService.login(form.getMailAddress(),form.getPassword())==null) {
			model.addAttribute("message","メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		}
		else {
			session.setAttribute("administratorName",form);
			return "forward:/employee/showList";
		}
		
	}
	/**
	 * HttpSession の invalidate()メソッドでセッション情報をクリアする
	 * @return　ログイン画面にリダイレクト
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

}
