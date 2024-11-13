package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 従業員更新時に使用するフォーム
 *
 * @author T.Araki
 */
public class UpdateEmployeeForm {

    /** 従業員ID */
    private String id;

    /** 名前 */
    @NotBlank(message = "氏名を入力してください")
    private String name;

    /** 画像 */
    private String image;

    /** 性別 */
    @NotBlank(message = "性別を入力してください")
    private String gender;

    /** 入社日（型はこれでOK？） */
    @NotBlank(message = "日付を選択してください")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "日付形式で入力してください")
    private String hireDate;

    /** メールアドレス */
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メール形式で入力してください")
    private String mailAddress;

    /** 郵便番号 */
    @NotBlank(message = "郵便番号を入力してください")
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号形式(ハイフンあり)で入力してください")
    private String zipCode;

    /** 住所 */
    @NotBlank(message = "住所を入力してください")
    private String address;

    /** 電話番号 */
    @NotBlank(message = "電話番号を入力してください")
    @Pattern(regexp = "^[0-9]{2,4}-[0-9]{2,4}-[0-9]{4}$", message = "電話番号形式(ハイフンあり)で入力してください")
    private String telephone;

    /** 給料 */
    @NotBlank(message = "給料を入力してください")
    @Pattern(regexp = "^[0-9]+$", message = "数値で入力してください")
    private String salary;

    /** 特性 */
    @NotBlank(message = "特性を入力してください")
    private String characteristics;

    /** 扶養人数 */
    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String dependentsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", name=" + name + ", image=" + image + ", gender=" + gender
                + ", hireDate=" + hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address="
                + address + ", telephone=" + telephone + ", salary=" + salary + ", characteristics=" + characteristics
                + ", dependentsCount=" + dependentsCount + "]";
    }

}
