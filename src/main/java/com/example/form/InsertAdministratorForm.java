package com.example.form;

public class InsertAdministratorForm {
    // 名前を受け取る
    private String name;

    // メールアドレスを受け取る
    private String mailAddress;

    // パスワードを受け取る
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
        return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", passwprd=" + password
                + "]";
    }

    
    
}
