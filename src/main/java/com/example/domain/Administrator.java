package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 管理者情報を表すモデル
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Administrator {
    private Integer id;
    private String name;
    private String mailAddress;
    private String password;
}
