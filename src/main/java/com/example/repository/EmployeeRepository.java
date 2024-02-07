package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

@Repository
public class EmployeeRepository {
  private final String FIND_ALL = """
      SELECT
        id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count
      FROM
        employees
      ORDER BY
        employees.hire_date ASC;
      """;
  private final String LOAD = """
    SELECT
      id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count
    FROM
      employees
    WHERE
      id = :id;
      """;
  private final String UPDATE = """
    UPDATE 
      employees 
    SET 
      name = :name ,
      image = :image,
      gender = :gender,
      hire_date = :hire_date,
      mail_address = :mail_address,
      zip_code = :zip_code,
      address = :address,
      telephone = :telephone,
      salary = :salary,
      characteristics = :characteristics,
      dependents_count = :dependents_count
    WHERE 
      id = :id;  
      """;
  public RowMapper<Employee> ROW_MAPPER = (rs,rowNum) -> {
    Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getString("gender"), rs.getDate("hireDate"), rs.getString("mail_address"), rs.getString("zip_code"), rs.getString("address"), rs.getString("telephone"), rs.getInt("salary"), rs.getString("characteristics"), rs.getInt("dependents_count"));
    return employee;
  };
  @Autowired
  private NamedParameterJdbcTemplate template;
  
  public List<Employee> findAll(){
    try{
      return template.query(FIND_ALL, ROW_MAPPER);
    }catch(Exception e){
      System.err.println(e.getMessage());
      return null;
    }
  }
  public Employee load(Integer id){
    SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
    try{
      return template.queryForObject(LOAD, params, ROW_MAPPER);
    }catch(Exception e){
      System.err.println(e.getMessage());
      return null;
    }
  }
  public void update(Employee employee){
    SqlParameterSource params = new BeanPropertySqlParameterSource(employee);
    try {
      template.update(UPDATE,params);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
