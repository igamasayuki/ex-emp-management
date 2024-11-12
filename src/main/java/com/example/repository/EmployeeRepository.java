package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリクラス
 */
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = 
    (rs,i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString(rs.getString("image")));
        employee.setGender(rs.getString(rs.getString("gender")));
        employee.setHireDate(rs.getTimestamp("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependentsCount "));

        return employee;
    };

    /**
     * 全件検索を行う
     * @return　全従業員一覧
     */
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees ORDER BY hire_date DESC";
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

        return employeeList;
    } 

    /**
     * 主キー検索を行う
     * @param id
     * @return　検索された従業員情報
     */
    public Employee load(Integer id) {
        String sql = "SELECT * FROM employees WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

        return employee;
    } 


    /**
     * 管理者情報を更新する
     * 
     * @param employee　管理者情報
     */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        if(employee.getId() == null) {
            System.out.println("データがありません");
        } else {
            String sql = "UPDATE emoloyees SET name=:name,image=:image,gender=:gender,hire_date=:hireDate,mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephoe,salary=:salary,characteristics=:characteristics,dependents_count=:dependentsCount WHERE=id=:id";

            template.update(sql, param);
        } 
    }  
}
