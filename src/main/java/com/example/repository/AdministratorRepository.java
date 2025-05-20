package com.example.repository;

import com.example.domain.Administrator;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdministratorRepository {
    private final NamedParameterJdbcTemplate template;
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, rowNum) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 管理者情報を登録する
     * @param administrator 登録する管理者情報
     */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String sql = "INSERT INTO administrators (name, mail_address, password) VALUES (:name, :mailAddress, :password)";
        template.update(sql, param);
    }

    /**
     * 管理者情報をメールアドレスとパスワードで検索する
     * @param mailAddress 管理者のメールアドレス
     * @param password 管理者のパスワード
     * @return 管理者情報のリスト
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT id, name, mail_address, password FROM administrators WHERE mail_address = :mailAddress AND password = :password";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);
        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        if(administratorList.isEmpty()) {
            return null;
        } else {
            return administratorList.get(0);
        }
    }
}
