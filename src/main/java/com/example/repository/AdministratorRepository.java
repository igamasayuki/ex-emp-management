package com.example.repository;

import java.util.List;

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
		String sql = """
            INSERT INTO 
                administrators
                    (
                        name,
                        mail_address,
                        password
                    )
            values
                (
                    :name,
                    :mailAddress,
                    :password
                 );
                """;
		template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者を取得(何もない場合はnullを返す).
     * @param mailAddress
     * @param password
     * 
     * @return 管理者情報 or NULL
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String query = """
            SELECT 
                mail_address,
                password 
            from 
                administrators 
            WHERE 
                mail_address = :mail_address 
            AND 
                password = :password
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("password", password).addValue("mail_address", mailAddress);
        List<Administrator> administratorList = template.query(query, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
		}
		return administratorList.get(0);
    }
}
