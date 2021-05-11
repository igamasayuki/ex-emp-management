package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private HttpSession session;

	@Autowired
	private AdministratorService administratorService;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form, RedirectAttributes redirectAttributes) {
		// Administratorドメインをインスタンス化する
		Administrator administrator = new Administrator();

		// リクエストパラメータが入っているInsertAdministratorFormオブジェクトの
		// 中身を今インスタンス化したAdministratorドメインオブジェクトにコピーする
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);

		// (ログイン画面)にリダイレクトする
		return "redirect:/administrator/insert";
	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		// LoginFormをインスタンス化しそのままreturn
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}

	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {

		// LoginFormオブジェクトの中にあるメールアドレスとパスワードを取り出す
		String mailAddress = form.getMailAddress();
		String password = form.getPassword();
//		AdministratorService administratorService = new AdministratorService();

		// administratorServiceのlogin()メソッドを呼ぶ。Administratorオブジェクトが戻り値として返ってくるのでそれを受け取る
		Administrator administrator = administratorService.login(mailAddress, password);

		// 戻り値がnullでなければログイン成功のため、以下を行う
		if (administrator != null) {
			// セッションスコープにadministratorNameという名前をつけて管理者名を格納する
			session.setAttribute("administratorName", administrator);

			// 一覧ページにフォワードする処理を記述する。
			return "forward:/employee/showList";
		} else {

			// 「メールアドレスまたはパスワードが不正です。」というエラーメッセージを
			// Modelオブジェクト(リクエストスコープ)にセット
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です。");

			// ログイン画面にフォワード
			return "administrator/login";
		}

	}

}
