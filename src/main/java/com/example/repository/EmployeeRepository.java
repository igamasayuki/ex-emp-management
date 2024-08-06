package com.example.repository;

import java.sql.ResultSet;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

/**
 * 従業員を操作するリポジトリクラス
 */
@Repository
public class EmployeeRepository {
    private final RowMapper<Employee> rowMapper = (ResultSet rs, int rowNum) -> {
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

    /**
     * 従業員一覧を入社日順(降順)で取得するメソッド
     * @return 従業員一覧
     */
    public List<Employee> findAll() {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count "
                +
                "FROM employees ORDER BY hire_date DESC";
        List<Employee> employees = template.query(sql, Collections.emptyMap(), rowMapper);
        return employees;
    };

    /**
     * 主キーから従業員情報を取得するメソッド
     * 
     * @param id
     * @return 従業員オブジェクト
     */
    public Employee load(Integer id) {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count "
                +
                "FROM employees WHERE id = :id";
        Map<String, Object> params = Map.of("id", id);
        return template.queryForObject(sql, params, rowMapper);
    };

    /**
     * 従業員情報を変更するメソッド
     * 
     * @param employee 更新する従業員オブジェクト
     */
    public void update(Employee employee) {
        String sql = "UPDATE employees SET " +
                "name = :name, " +
                "image = :image, " +
                "gender = :gender, " +
                "hire_date = :hireDate, " +
                "mail_address = :mailAddress, " +
                "zip_code = :zipCode, " +
                "address = :address, " +
                "telephone = :telephone, " +
                "salary = :salary, " +
                "characteristics = :characteristics, " +
                "dependents_count = :dependentsCount " +
                "WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", employee.getId());
        params.put("name", employee.getName());
        params.put("image", employee.getImage());
        params.put("gender", employee.getGender());
        params.put("hireDate", new java.sql.Date(employee.getHireDate().getTime()));
        params.put("mailAddress", employee.getMailAddress());
        params.put("zipCode", employee.getZipCode());
        params.put("address", employee.getAddress());
        params.put("telephone", employee.getTelephone());
        params.put("salary", employee.getSalary());
        params.put("characteristics", employee.getCharacteristics());
        params.put("dependentsCount", employee.getDependentsCount());
        template.update(sql, params);
    }
}
