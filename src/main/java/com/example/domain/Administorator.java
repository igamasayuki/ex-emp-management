package com.example.domain;

/**
 * 管理者情報を表すドメイン
 *
 * @author T.Araki
 */
public class Administorator {

    /** ID */
    private Integer ID;

    /** 名前 */
    private String name;

    /** メールアドレス */
    private String mailAddress;

    /** パスワード */
    private String password;

    public Administorator() {
    }

    public Administorator(Integer iD, String name, String mailAddress, String password) {
        ID = iD;
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

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
        return "Administorator [ID=" + ID + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }

}
