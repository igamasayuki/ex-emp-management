package jp.co.sample.domain;

import java.util.Date;

public class Employee {
	
	/**従業員情報を表すドメイン*/

	/**id*/
	private Integer id;
	/**名前*/
	private String name;
	/**画像*/
	private String image;
	/**性別*/
	private String gender;
	/**入社日*/
	private java.util.Date  hireDate;
	/**メールアドレス*/
	private String mailAddress;
	/**郵便番号*/
	private String zipCode;
	/**住所*/
	private String address;
	/**電話番号*/
	private String telephone;
	/**給料*/
	private Integer salary;
	/**特性*/
	private String characteristics;
	/**不要人数*/
	private Integer dependentsCount;
	
	public static void Employee() {
	}
	

	public Employee(Integer id, String name, String image, String gender, Date hireDate, String mailAddress,
			String zipCode, String address, String telephone, Integer salary, String characteristics,
			Integer dependentsCount) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.gender = gender;
		this.hireDate = hireDate;
		this.mailAddress = mailAddress;
		this.zipCode = zipCode;
		this.address = address;
		this.telephone = telephone;
		this.salary = salary;
		this.characteristics = characteristics;
		this.dependentsCount = dependentsCount;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate="
				+ hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address
				+ ", telephone=" + telephone + ", salary=" + salary + ", characteristics=" + characteristics
				+ ", dependentsCount=" + dependentsCount + "]";
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

	public final String getImage() {
		return image;
	}

	public final void setImage(String image) {
		this.image = image;
	}

	public final String getGender() {
		return gender;
	}

	public final void setGender(String gender) {
		this.gender = gender;
	}

	public final java.util.Date getHireDate() {
		return hireDate;
	}

	public final void setHireDate(java.util.Date hireDate) {
		this.hireDate = hireDate;
	}

	public final String getMailAddress() {
		return mailAddress;
	}

	public final void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public final String getZipCode() {
		return zipCode;
	}

	public final void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final String getTelephone() {
		return telephone;
	}

	public final void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public final Integer getSalary() {
		return salary;
	}

	public final void setSalary(Integer salary) {
		this.salary = salary;
	}

	public final String getCharacteristics() {
		return characteristics;
	}

	public final void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public final Integer getDependentsCount() {
		return dependentsCount;
	}

	public final void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	
	
}
