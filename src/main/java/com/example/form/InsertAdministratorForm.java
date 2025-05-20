package com.example.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 管理者情報を登録するためのフォーム
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InsertAdministratorForm {
    private String name;
    private String mailAddress;
    private String password;
}
