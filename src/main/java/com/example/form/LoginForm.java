package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author 金丸天
 *         登録に必要なリクエストパラメータを受け取る属性
 */

public class LoginForm {
    @NotBlank(message = "空欄になっています")
    @Email(message = "メールアドレスが不正です")
    private String mailAddress;

    @NotBlank(message = "空欄になっています")
    private String password;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
    }

}
