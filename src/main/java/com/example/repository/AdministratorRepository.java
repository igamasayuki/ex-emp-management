package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.model.Administrator;

@Repository
public class AdministratorRepository {
  RowMapper<Administrator> ROW_MAPPER = (rs, rowNum) -> {
    Administrator administrator = new Administrator(rs.getInt("id"),rs.getString("name"),rs.getString("mailAddress"),rs.getString("password"));
    return administrator;
  };
  static final String INSERT_QUERY = """
      INSERT INTO
        administrators(name,mail_address,password)
      VALUES(:name,:mail_address,password);
      """;
  static final String FIND_QUERY = """
    SELECT
      id,name,mail_address,password
    FROM
      administrators
    WHERE
    mail_address = :mail_address
    AND
    password = :password
    ;
      """;
  @Autowired
  private NamedParameterJdbcTemplate template;

  public void insert(Administrator administrator){
    try{
      SqlParameterSource params = new BeanPropertySqlParameterSource(administrator);
      template.update(INSERT_QUERY, params);
    }catch(Exception e){
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }
  public List<Administrator> findByMailAddressAndPassword (String mailAddress, String password){
    SqlParameterSource params = new MapSqlParameterSource().addValue("mail_address", mailAddress).addValue("password", password);
    try {
      return template.query(FIND_QUERY,params,ROW_MAPPER);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
      return null;
    }
  }
}
