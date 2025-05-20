package com.example.repository;

import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;


@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * EmployeeオブジェクトにマッピングするRowMapperです.
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
            = (rs, rowNum) -> {

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
     * すべての従業員を入社日の降順で取得します.
     *
     * @return　従業員のリスト。　従業員が存在しない場合は空のリストを返します。
     */

    public List<Employee> findAll(){
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, " +
                "address, telephone, salary, characteristics, dependents_count " +
                "FROM employees ORDER BY hire_date DESC";

        List<Employee> employeeList = template.query(sql ,EMPLOYEE_ROW_MAPPER);

        return employeeList;
    }

    /**
     * 指定されたIDに対応する従業員情報を取得します。
     *
     * @param id 検索する従業員ID
     * @return　該当する従業員情報。　IDに対応する従業員がいない場合はnullを返します。
     */

    public Employee findById(Integer id){

        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, " +
                "address, telephone, salary, characteristics, dependents_count " +
                "FROM employees WHERE id=:id";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        try {
            Employee employee =
                    template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
            return employee;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * 指定された従業員情報でデータベースを更新します。
     *
     * @param employee　更新する従業員情報。IDは更新対象の特定に使用します。
     */
    public void update(Employee employee){

        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        String updateSql = "UPDATE employees SET " +
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

        template.update(updateSql, param);
    }

}
