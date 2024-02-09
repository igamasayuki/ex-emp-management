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
    @Autowired
    private NamedParameterJdbcTemplate template;

    private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);

    /**
     * 従業員リスト一覧を取得.
     * @return 従業員一覧リスト
     */
    public List<Employee> findAll() {
        String query = """
                SELECT 
                    *
                from
                    employees
                order by
                    hire_date desc
                """;
        List<Employee> employeeList = template.query(query, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    /**
     * 主キーから従業員情報を取得.
     * @param id
     * @return 従業員情報
     */
    public Employee load(Integer id) {
        String query = """
            SELECT 
                *
            FROM
                employees
            WHERE
                id = :id
            ORDER BY
                hire_date DESC
                """;
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(query, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /**
     * 従業員情報を変更する.
     * @param employee
     */
    public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		String updateQuery = """
                UPDATE 
                    employees 
                SET 
                    id = :id, 
                    name = :name, 
                    image = :image, 
                    gender = :gender, 
                    hire_date = :hireDate, 
                    mail_address = :mailAddress, 
                    zip_code = :zipCode, 
                    address = :address, 
                    telephone = :telephone, 
                    salary = :salary, 
                    characteristics = :characteristics, 
                    dependents_count = :dependentsCount 
                WHERE 
                    id = :id
                """;
		template.update(updateQuery, param);
    }

}
