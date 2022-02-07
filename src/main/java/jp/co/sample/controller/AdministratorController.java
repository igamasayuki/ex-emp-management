package jp.co.sample.controller;

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
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService service;

	@Autowired
	public HttpSession session;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * ログインページへフォワード
	 *
	 * @return ログインページ
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * ログインできるか、メールアドレスとパスワードを使ってユーザ登録確認を行う
	 *
	 * @param loginForm ユーザ入力ログイン情報
	 * @return ログインページ
	 */
	@RequestMapping("login")
	public String login(LoginForm loginForm, Model model) {
		Administrator admin = service.findByMailAddressAndPassword(loginForm.getMailAddress(),
				loginForm.getPassword());
		if (admin == null) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが不正です");
			return "administrator/login";
		} else {
			session.setAttribute("administratorName", admin.getName());
			return "forward:/employee/showList";
		}
	}

	/**
	 * 従業員を登録
	 *
	 * @return 登録完了画面ヘフォワード
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者登録をしてログインフォームへリダイレクト
	 *
	 * @param form 管理者受け取りフォーム
	 * @return ログインフォームページ
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		service.insert(administrator);
		return "redirect:/";
	}
}