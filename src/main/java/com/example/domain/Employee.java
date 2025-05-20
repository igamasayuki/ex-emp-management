package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 従業員情報を表すモデル
 */
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private String image;
    private String gender;
    private Date hireDate;
    private String mailAddress;
    private String zipCode;
    private String address;
    private String telephone;
    private Integer salary;
    private String characteristics;
    private Integer dependentsCount;
}
