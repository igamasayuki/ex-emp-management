package com.example.repository;

import com.example.domain.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));

        return employee;
    };

    private NamedParameterJdbcTemplate template;

    public List<Employee> findAll(){
        String sql = "SELECT * FROM employees ORDER BY hire_date DESC;";
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

        if (employeeList.isEmpty()){
            return null;
        }
        return employeeList;
    }

}
