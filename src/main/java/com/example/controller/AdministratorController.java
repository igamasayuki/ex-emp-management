package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private HttpSession session;
    
    /**
     * 管理者登録画面を表示する
     * @param insertAdministratorForm
     * @return 管理者登録画面へフォワードする
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm) {
        
        return "administrator/insert";
    }

    /**
     * 管理者情報登録処理
     * @param insertAdministratorForm 登録する管理者情報
     * @return ログイン画面へリダイレクトする
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm insertAdministratorForm) {
        // Administratorエンティティをインスタンス化
        Administrator administrator = new Administrator();
        // 受け取ったフォームパラメータをエンティティへコピーする
        BeanUtils.copyProperties(insertAdministratorForm, administrator);

        // 登録処理
        administratorService.insert(administrator);

        // ログイン画面へリダイレクトする
        return "redirect:administrator/login";
    }
    /**
     * ログインへ遷移する
     * @param loginForm ログインフォーム
     * @return ログイン画面へフォワードする
     */
    @GetMapping("/")
    public String toLogin(LoginForm loginForm) {
        return "administrator/login";
    }
    /**
     * ログインする
     * 戻り値がnullならログイン失敗、modelへエラーメッセージを格納し、ログイン画面へフォワードする
     * メールアドレスとパスワードの組み合わせで該当するユーザーが存在する場合、従業員情報一覧へリダイレクトする
     * @param loginForm リクエストパラメータ(mailAddress, password)
     * @param model リクエストスコープ
     * @return 従業員情報一覧へリダイレクトする
     */
    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model) {
        
        // AdministratorにServiceのメソッド戻り値を代入する
        Administrator administrator = 
        administratorService.findByMailAddressAndPassword(
            // 受け取ったメールアドレス  パスワード
            loginForm.getMailAddress(), loginForm.getPassword()
            );

        // Serviceの戻り値がnullならログイン失敗、modelへエラーメッセージを格納し、ログイン画面へフォワードする
        if (administrator == null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です");
            return toLogin(loginForm);
        }
        
        // ログインするユーザー名をセッションスコープへ格納する
        session.setAttribute("administratorName", administrator.getName());

        // 従業員情報一覧へリダイレクトする
        return "redirect:/employee/showList";
    }

}
