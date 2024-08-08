package com.example.repository;

import java.sql.ResultSet;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/**
 * 管理者を操作するリポジトリクラス
 */
@Repository
public class AdministratorRepository {

    private RowMapper<Administrator> rowMapper = (ResultSet rs, int rowNum) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_Address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 管理者情報を挿入するメソッド
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        String sql = "INSERT INTO administrators (name, mail_Address, password) VALUES (:name, :mailAddress, :password)";
        Map<String, Object> params = Map.of(
                "name", administrator.getName(),
                "mailAddress", administrator.getMailAddress(),
                "password", administrator.getPassword());
        template.update(sql, params);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得するメソッド
     * @param mailAddress 入力されたメールアドレス
     * @param password 入力されたパスワード
     * @return administratorList 管理者情報のリスト
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT * FROM administrators WHERE mailAddress = :mailAddress AND password = :password";
        Map<String, Object> params = Map.of(
                "mailAddress", mailAddress,
                "password", password);
        List<Administrator> administratorList = template.query(sql, params, rowMapper);
        if (administratorList.isEmpty()) {
            return null;
        }
        return administratorList.get(0);
    }
}
