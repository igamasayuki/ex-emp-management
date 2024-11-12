package com.example.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    // AdministratorオブジェクトのためのRowMapperを定義
    private final RowMapper<Administrator> rowMapper = (rs, i) -> {
        Administrator administrator = new Administrator(i, null, null, null);
        // ResultSet (rs) からadministratorのプロパティを設定
        administrator.setId(rs.getInt("id"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    // 管理者情報を挿入するメソッド
    public void insert(Administrator administrator) {
        String sql = "INSERT INTO administrators (name,mail_address, password) VALUES (:name, :mailAddress, :password)";
        // パラメータをマップにセット
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", administrator.getName()); // name をパラメータとして追加
        paramMap.put("mailAddress", administrator.getMailAddress());
        paramMap.put("password", administrator.getPassword());

        // NamedParameterJdbcTemplate でクエリを実行
        template.update(sql, paramMap);
    }

    // メールアドレスとパスワードから管理者情報を取得するメソッド
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT * FROM administrators WHERE mail_address = :mailAddress AND password = :password";
        Map<String, Object> params = new HashMap<>();
        params.put("mailAddress", mailAddress);
        params.put("password", password);

        List<Administrator> administratorList = template.query(sql, params, rowMapper);
        if (administratorList.size() == 0) {
            return null; // 該当なしの場合はnullを返す
        }
        return administratorList.get(0); // 最初の結果を返す
    }
}
