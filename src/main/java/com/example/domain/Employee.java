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

}
