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

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

  private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);

  @Autowired
  private NamedParameterJdbcTemplate template;

  /**
   * 従業員一覧情報を入社日順(降順)で取得 する(従業員が存在しない場合はサイズ 0 件の従業員一覧を返す)。
   * @return
   */
  public List<Employee> findAll() {
    String sql = "SELECT * FROM employees ORDER BY age";
    List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

    return employeeList;
  }

  /**
   * 主キーから従業員情報を取得する(従業員 が存在しない場合は Spring が自動的に例 外を発生します)。
   * @param id
   * @return
   */
  public Employee load(Integer id) {
    String sql = "SELECT * FROM employees WHERE id=:id";
    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
    Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

    return employee;
  }

  /**
   * 従業員情報を変更する(id カラムを除いた 従業員情報の全てのカラムを更新できる ような SQL を発行する)。全行更新されな いように Where 句の指定を考える。
   * @param employee
   */
  public void update(Employee employee) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
    if (employee.getId() == null) {
      String insertSql = "INSERT INTO employees(name,image,gender,hireDate,mailAddress,zipCode,address,telephone,salary,characteristics,dependentsCount) + VALUES(:name,:image,:gender,:hireDate,:mailAddress,:zipCode,:address,:telephone,:salary,:characteristics,:dependentsCount)";
      template.update(insertSql, param);
    } else {
      String updateSql = "UPDATE employees SET name=:name,image=:image,gender=:gender,hireDate=:hireDate,mailAddress=:mailAddress,zipCode=:zipCode,address=:address,telephone=:telephone,salary=:salary,characteristics=:characteristics,dependentsCount=:dependentsCount WHERE id=:id";
      template.update(updateSql, param);
    }
  }

}
