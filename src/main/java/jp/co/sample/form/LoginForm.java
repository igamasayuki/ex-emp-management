package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

public class LoginForm {
	/**
	 * フィールド属性
	 */
	@NotBlank(message="メールアドレスを入力してください")
	private String mailAddress;
	@NotBlank(message="パスワードを入力してください")
	private String password;
	
	@Override
	public String toString() {
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
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
}