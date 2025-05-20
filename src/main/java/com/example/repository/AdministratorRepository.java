package com.example.repository;

import com.example.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * administratorsテーブルを操作するリポジトリ
 */
@Repository
public class AdministratorRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
            = (rs, rowNum) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mailAddress"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**管理者情報を挿入するまたは更新する
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator){

        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

        if(administrator.getId() == null){
            String insertSql = "inset into administrators(name, mailAddress, password) " +
                    "values(:name, :mailAddress, password)";
            template.update(insertSql, param);
        }else{
            String updateSQL = "update administrator " +
                    "set name=:name, mailAddress=:mailAddress, password=:password where id=:id);";
            template.update(updateSQL, param);
        }
    }

    /**
     * メールアドレスとパスワードから管理者権限を取得する
     * 1件もない場合はnullを返す
     * @param mailAddress
     * @param password
     * @return
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){

        String sql  = "select id, name, mailAddress, password " +
                "from administrator where mailAddress and password";


        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);


        try {
            Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
            return administrator;

        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }
}
