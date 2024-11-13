package com.example.repository;

/**
 * @author harasawakana
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.example.domain.Employee;

@Repository

public class EmployeeRepository {
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

    /** 従業員⼀覧情報を入社日順で取得 */
    public List<Employee> findAll() {
        String insertSql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics FROM administarters ORDER BY DESC;";
        List<Employee> employeeList = template.query(insertSql, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    /** 従業員情報を取得 */
    public Employee load(Integer id) {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics FROM administarters WHERE id=:id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /** 従業員情報を変更・更新 */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String updateSql = "UPDATE administarters SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount WHERE id=:id;";
        template.update(updateSql, param);
        return;
    }
}
