package jp.co.sample.form;

public class LoginForm {
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;

	public LoginForm() {}

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
