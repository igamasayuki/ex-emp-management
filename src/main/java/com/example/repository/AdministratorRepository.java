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
  private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(
      Administrator.class);

  @Autowired
  private NamedParameterJdbcTemplate template;

  /**
   * 管理者情報を挿入する。
   * 
   * @param administrator 管理者情報
   */
  public void insert(Administrator administrator) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
    String sql = "INSERT INTO administrators(name, mailAddress, password) VALUES(:name,:mailAddress,:password)";
    template.update(sql, param);
  };

  /**
   * メールアドレスとパスワ ードから管理者情報を取 得する(1 件も存在しない 場合は null を返す※)。
   * 
   * @param mailAddress
   * @param password
   * @return
   */
  public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
    String sql = "SELECT * FROM administrators WHERE mailAddress=:mailAdress AND password=:password";
    SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
        password);
    try {
      return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
    } catch (Exception e) {
      return null;
    }
  }

}
