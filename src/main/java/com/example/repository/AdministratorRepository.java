package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(Administrator.class);

    /**
     * 管理者情報をインサートする.
     * 
     * @param administrator
     */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append("INSERT INTO administrators(id,name,mail_address,password)");
        insertQuery.append("VALUES(:id,:name,:mail_address,:password)");
        template.update(insertQuery.toString(), param);
    }

    /**
     * メールアドレスとパスワードから管理者を取得(何もない場合はnullを返す).
     * @param mailAddress
     * @param password
     * 
     * @return 管理者情報 or NULL
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        StringBuilder query = new StringBuilder().append("SELECT mail_address,password from administrators");
        query.append("WHERE mail_address = :mail_address AND password = :password");
        SqlParameterSource param = new MapSqlParameterSource(query.toString(),ADMINISTRATOR_ROW_MAPPER);
        Administrator administrator = template.queryForObject(query.toString(), param, ADMINISTRATOR_ROW_MAPPER);
        if(administrator == null) {
            return null;
        } else {
            return administrator;
        }
    }
}
