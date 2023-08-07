package com.example.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        
    }
}
