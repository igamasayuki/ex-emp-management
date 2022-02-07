package jp.co.sample.domain;

public class Administrator {
	
	/**管理者情報を表すドメイン*/
	/**id*/
	private Integer id;
	/**名前*/
	private String name;
	/**アドレス*/
	private String mail;
	/**パスワード*/
	private String pass;
	
	
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", mail=" + mail + ", pass=" + pass + "]";
	}
	
	public final Integer getId() {
		return id;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getMail() {
		return mail;
	}
	public final void setMail(String mail) {
		this.mail = mail;
	}
	public final String getPass() {
		return pass;
	}
	public final void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
