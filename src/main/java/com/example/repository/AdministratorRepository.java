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
 * administratorsテーブルを操作するリポジトリ.
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
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));

        return administrator;
    };

    /**
     * 管理者情報を挿入するまたは更新する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator){

        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

        if(administrator.getId() == null){
            String insertSql = "INSERT INTO administrators(name, mail_address, password) " +
                    "VALUES(:name, :mailAddress, :password)";
            template.update(insertSql, param);
        }else{
            String updateSql = "UPDATE administrators " +
                    "SET name=:name, mail_address=:mailAddress, password=:password WHERE id=:id);";
            template.update(updateSql, param);
        }
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する.
     * 1件もない場合はnullを返す
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 管理者情報
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){

        String sql  = "SELECT id, name, mail_address, password " +
                "FROM administrators where mail_address AND password";


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
