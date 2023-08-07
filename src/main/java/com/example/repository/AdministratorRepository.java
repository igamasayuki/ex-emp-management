package com.example.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    // JDBCテンプレートを生成
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;
    // RowMapperを定義
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
    = new BeanPropertyRowMapper<>(Administrator.class);
    // 管理者情報を挿入する
    public void insert(Administrator administrator) {

        String sql = "INSERT INTO administrators(name,mail_address,password) " +
        "(:name, :mailAddress, :password);";
        
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("name", administrator.getName())
            .addValue("mailAddress", administrator.getMailAddress())
            .addValue("password", administrator.getPassword());
        
        // idが渡されていない場合は、INSERTし
        // idが渡されている場合は、UPDATEする
        if (administrator.getId()==null) {
            namedTemplate.update(sql, param);
        } else {
            sql = "UPDATE administrators SET " +
                "name = :name, mail_address = :mailAddress, password = :password " +
                "WHERE id = :id;";
            param = new MapSqlParameterSource()
                .addValue("id", administrator.getId())
                .addValue("name", administrator.getName())
                .addValue("mailAddress", administrator.getMailAddress())
                .addValue("password", administrator.getPassword());
            namedTemplate.update(sql, param);
        }
    }

    // ログイン押下時に、渡されたメールアドレスとパスワードで
    // 一致する管理者情報を取得する
    // 存在しない場合は、nullを返す
    public Administrator findByMailAddressAndPassword(
        String mailAddress, String password) {
        
        // sqlを宣言する
        String sql =
        "SELECT id, name, mail_address, password FROM administrators " +
        "WHERE mail_address = :mailAddress AND password = :password;";
        
        // MapSqlParameterSource
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("mailAddress", mailAddress)
            .addValue("password",password);
        // nullの可能性があるため、Listで受ける
        List<Administrator> administrators = namedTemplate.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
            
        // 1件もヒットしない場合はnullを返し、ヒットした場合は先頭のAdministratorを返す
        if (administrators.size()==0) {
            return null;
        } else {
            return administrators.get(0);
        }
    }

}
