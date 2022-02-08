package jp.co.sample.form;

public class InsertAdministratorForm {
<<<<<<< HEAD

	private String name;
	private String mailAddress;
	private String password;
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getMailAddress() {
		return mailAddress;
	}
	public final void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	
	
}
=======
	private String name;
	private String mailAddress;
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
>>>>>>> develop
