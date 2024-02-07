package com.example.repository;

import java.util.ArrayList;
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

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static String FIND_ALL_SQL = """
            SELECT
                id,
                name,
                image,
                gender,
                hire_date,
                mail_address,
                zip_code,
                address,
                telephone,
                salary,
                characteristics,
                dependents_count
            FROM
                employees
                """;

    private static String LOAD_SQL = FIND_ALL_SQL + "WHERE id = :id";

    private static String UPDATE_SQL = """
            UPDATE
                employees
            SET
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
                id = :id;
                """;

    /**
     * 従業員一覧情報を入社日順（降順）で取得する.
     * 
     * @return 従業員リストを返す. 存在しない場合はサイズ0件の従業員一覧を返す.
     */
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        employees = template.query(FIND_ALL_SQL, EMPLOYEE_ROW_MAPPER);
        return employees;
    }

    /**
     * 主キーから従業員情報を取得する.
     * 
     * @param id 主キー
     * @return Employee 存在しない場合は例外.
     */
    public Employee load(Integer id) {
        Employee employee = new Employee();
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        employee = template.queryForObject(LOAD_SQL, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /**
     * 従業員情報を変更する.
     * 
     * @param employee
     */
    public void update(Employee employee) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(employee);
        template.update(FIND_ALL_SQL, params);
    }

}
