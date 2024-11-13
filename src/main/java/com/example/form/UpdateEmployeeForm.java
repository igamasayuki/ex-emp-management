package com.example.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @Author: 金丸天
 *          リクエストパラメータを受け取るクラス
 * @param id:              ID
 * @param dependentsCount: 扶養⼈数
 */
public class UpdateEmployeeForm {

    private String id;
    @NotBlank(message = "扶養人数が空欄になっています")
    private String dependentsCount;
    @NotBlank(message = "名前が空欄になっています")
    private String name;
    private String image;
    @NotBlank(message = "性別が空欄になっています")
    private String gender;
    @NotBlank(message = "入社日が空欄になっています")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    @NotBlank(message = "メールアドレスが空欄になっています")
    private String mailAddress;
    @NotBlank(message = "番地が空欄になっています")
    private String zipCode;
    @NotBlank(message = "住所空欄になっています")
    private String address;
    @NotBlank(message = "電話番号が空欄になっています")
    private String telephone;
    @NotNull(message = "給与を入力してください")
    private Integer salary;
    @NotBlank(message = "値が空欄になっています")
    private String characteristics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
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

    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + ", name=" + name + ", image="
                + image + ", gender=" + gender + ", hireDate=" + hireDate + ", mailAddress=" + mailAddress
                + ", zipCode=" + zipCode + ", address=" + address + ", telephone=" + telephone + ", salary=" + salary
                + ", characteristics=" + characteristics + "]";
    }

}
