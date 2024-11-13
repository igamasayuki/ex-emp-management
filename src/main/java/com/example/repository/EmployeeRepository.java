package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author M.aoki
 * 
 */
@Repository
public class EmployeeRepository {

  private static final String TABLE_NAME = "employees";

  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
    Employee employee = new Employee();
    employee.setId(rs.getInt("id"));
    employee.setName(rs.getString("name"));
    employee.setImage(rs.getString("image"));
    employee.setGender(rs.getString("gender"));
    employee.setHireDate(rs.getDate("hire_date"));
    employee.setMailAddress(rs.getString("mail_address"));
    employee.setZipCode(rs.getString("zip_code"));
    employee.setAddress(rs.getString("address"));
    employee.setTelephone(rs.getString("telephone"));
    employee.setSalary(rs.getInt("salary"));
    employee.setCharacteristics(rs.getString("characteristics"));
    employee.setDependentsCount(rs.getInt("dependents_count"));

    return employee;
  };

  /**
   * 従業員一覧情報の検索.
   * 
   * @return 全従業員情報
   * 
   */
  public List<Employee> findAll() {
    String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM "
        + TABLE_NAME + " ORDER BY hire_date DESC";

    List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

    return employeeList;
  }

  /**
   * 主キーから従業員情報を検索.
   * 
   * @param id 従業員ID
   * @return 従業員情報
   * 
   */
  public Employee load(Integer id) {
    String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM "
        + TABLE_NAME + " WHERE id = :id";

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("id", id);

    Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

    return employee;
  }

  /**
   * 従業員情報を変更する.
   * 
   * @param employee 変更したい従業員情報
   */
  public void update(Employee employee) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

    String sql = "UPDATE " + TABLE_NAME +
        " SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount "
        + " WHERE id = :id";

    template.update(sql, param);

  }

}
