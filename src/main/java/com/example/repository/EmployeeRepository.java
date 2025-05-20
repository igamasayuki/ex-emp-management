package com.example.repository;

import com.example.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final NamedParameterJdbcTemplate template;
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, rowNum) -> {
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
     * 入社日順で全従業員情報を取得する
     * @return 従業員情報のリスト(従業員が存在しない場合は空のリストを返す)
     */
    public List<Employee> findAll() {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees ORDER BY hire_date DESC";
        return template.query(sql, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * idを基に従業員情報を取得する
     * @param id 従業員ID
     * @return 従業員情報(従業員が存在しない場合は例外がスローされる)
     */
    public Employee findById(Integer id) {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count FROM employees WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 従業員情報を更新する
     * @param employee 登録する従業員情報
     */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String sql = "UPDATE employees SET name = :name, image = :image, gender = :gender, hire_date = :hireDate, mail_address = :mailAddress, zip_code = :zipCode, address = :address, telephone = :telephone, salary = :salary, characteristics = :characteristics, dependents_count = :dependentsCount WHERE id = :id";
        template.update(sql, param);
    }
}
