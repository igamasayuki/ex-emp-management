package com.example.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    // データベースから全ての従業員を取得するメソッドの例
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return template.query(sql, employeeRowMapper);
    }

    // 主キーから従業員情報を取得するメソッド
    public Employee load(Integer id) {
        String sql = "SELECT * FROM employees WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, param, employeeRowMapper);
    }

    // 従業員情報を更新するメソッド
    public void update(Employee employee) {
        String sql = "UPDATE employees SET " +
                "name = :name, " +
                "image = :image, " +
                "gender = :gender, " +
                "hire_date = :hire_date, " +
                "mail_address = :mail_address, " +
                "zip_code = :zip_code, " +
                "address = :address, " +
                "telephone = :telephone, " +
                "salary = :salary, " +
                "characteristics = :characteristics, " +
                "dependents_count = :dependents_count " +
                "WHERE id = :id;";

        Map<String, Object> params = new HashMap<>();
        params.put("name", employee.getName());
        params.put("image", employee.getImage());
        params.put("id", employee.getId());

        template.update(sql, params);
    }

}
