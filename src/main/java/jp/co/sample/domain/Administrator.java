package jp.co.sample.domain;

public class Administrator {
	/**
	 * フィールド属性
	 */
	private Integer id;
	private String name;
	private String mailaddress;
	private String password;
	
	public Administrator() {}
	
	public Administrator(Integer id, String name, String mailaddress, String password) {
		super();
		this.id = id;
		this.name = name;
		this.mailaddress = mailaddress;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mailaddress=" + mailaddress + ", password=" + password
				+ "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}