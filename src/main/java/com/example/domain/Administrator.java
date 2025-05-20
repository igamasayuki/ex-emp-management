package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 管理者情報を表すモデル.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Administrator {
    /**  管理者ID */
    private Integer id;
    /**  管理者名 */
    private String name;
    /**  管理者メールアドレス */
    private String mailAddress;
    /**  管理者パスワード */
    private String password;
}
