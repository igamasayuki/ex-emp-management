package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 管理者情報登録時に使用するフォーム
 * 
 * @author M.aoki
 */
public class InsertAdministratorForm {
  /** 名前 */
  @NotBlank(message = "名前を入力してください")
  private String name;

  /** メールアドレス */
  @Size(min = 1, max = 48, message = "Eメールは1文字以上48文字以内で入力してください")
  @Email(message = "Eメールの形式が不正です")
  private String mailAddress;

  /** パスワード */
  @Size(min = 1, max = 48, message = "パスワードは1文字以上48文字で入力してください")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "パスワードは英字と数字を両方含む必要があります")
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
    return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + "]";
  }

}
