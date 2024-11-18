package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @Author:金丸天
 *             Administratorのフォームクラスを作成
 */
public class InsertAdministratorForm {

    @NotBlank(message = "空欄になっています")
    private String name;
    @NotBlank(message = "空欄になっています")
    @Email(message = "メールアドレスが不正です")
    private String mailAddress;
    @NotBlank(message = "空欄になっています")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }

}
