package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 従業員情報を表すモデル.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    /** 従業員ID */
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
    /** 従業員給与 */
    private Integer salary;
    /** 従業員特徴 */
    private String characteristics;
    /** 従業員扶養家族数 */
    private Integer dependentsCount;
}
