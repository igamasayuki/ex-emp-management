package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.model.Administrator;

@Repository
public class AdministratorRepository {

    private static final RowMapper<Administrator> ADMIN_ROW_MAPPER = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword("password");
        return administrator;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final String INSERT_SQL = """
            INSERT INTO administrators(
                name,
                mail_address,
                password
            )
            VALUES(
                :name,
                :mailAddress,
                :password
            );
                """;

    private static final String FIND_BY_MAIL_AND_PASSWORD_SQL = """
            SELECT
                id,
                name,
                mail_address,
                password
            FROM
                administrators
            WHERE
                mail_address = :mailAddress
            AND
                password = :password;
                """;

    /**
     * 管理者情報を入力する.
     * 
     * @param administrator 登録する管理者情報
     */
    public void insert(Administrator administrator) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", administrator.getName())
                .addValue("mailAddress", administrator.getMailAddress())
                .addValue("password", administrator.getPassword());

        template.update(INSERT_SQL, params);

    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     * 
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return administrator 該当する管理者情報を返す. 該当しなかった場合はnullを返す.
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("mailAddress", mailAddress)
                    .addValue("password", password);
            Administrator administrator = new Administrator();
            administrator = template.queryForObject(FIND_BY_MAIL_AND_PASSWORD_SQL, params, ADMIN_ROW_MAPPER);
            return administrator;

        } catch (Exception e) {
            return null;
        }

    }
}
