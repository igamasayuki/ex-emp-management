package com.example.domain;

import java.util.Date;

public class Employee {
  /** 従業員id */
  private Integer id;
  /** 従業員名 */
  private String name;
  /** 従業員画像 */
  private String image;
  /** 従業員性別 */
  private String gender;
  /** 従業員入社日 */
  private Date hireDate;
  /** 従業員メールアドレス */
  private String mailAddress;
  /** 従業員郵便番号 */
  private String zipCode;
  /** 従業員住所 */
  private String address;
  /** 従業員電話番号 */
  private String telephone;
  /** 従業員給料 */
  private Integer salary;
  /** 従業員特徴 */
  private String characteristics;
  /** 従業員扶養人数 */
  private Integer dependentsCount;

  public Employee() {
  }

  public Employee(Integer id, String name, String image, String gender, Date hireDate, String mailAddress,
      String zipCode, String address, String telephone, Integer salary, String characteristics,
      Integer dependentsCount) {
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
        + hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address + ", telephone="
        + telephone + ", salary=" + salary + ", characteristics=" + characteristics + ", dependentsCount="
        + dependentsCount + "]";
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public String getMailAddress() {
    return mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  public String getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(String characteristics) {
    this.characteristics = characteristics;
  }

  public Integer getDependentsCount() {
    return dependentsCount;
  }

  public void setDependentsCount(Integer dependentsCount) {
    this.dependentsCount = dependentsCount;
  }

  

}
