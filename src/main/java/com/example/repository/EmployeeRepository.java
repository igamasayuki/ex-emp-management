package com.example.repository;


import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

    // オブジェクトと行を紐づける
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
        = new BeanPropertyRowMapper<>(Employee.class);

    // データベースアクセス用テンプレート
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    /** 全件取得SQL */
    private static String findAllSql = """
        SELECT 
        id, name, image, gender, hire_date, mail_address, 
        zip_code, address, salary, characteristics, dependents_count 
        FROM employees ORDER BY hire_date DESC;
        """;

    /** 主キー検索SQL */
    private static String loadSql = """
        SELECT 
        id, name, image, gender, hire_date, mail_address, 
        zip_code, address, salary, characteristics, dependents_count 
        FROM employees 
        WHERE id = :id;
        """;
    /** 従業員情報更新SQL */
    private static String updateSql = """
            UPDATE employees 
            SET 
                name = :name, 
                image = :image, 
                gender = :gender, 
                hire_date = :hire_date, 
                mail_address = :mail_address, 
                zip_code = :zip_code, 
                address = :address, 
                salary = :salary, 
                characteristics = :characteristics, 
                dependents_count = dependents_count
            WHERE id = :id;
            """;
    /**
     * 従業員情報を全件取得する
     * @return employeeList 全従業員一覧
     */
    public List<Employee> findAll() {
        List<Employee> employeeList 
            = namedParameterJdbcTemplate.query(findAllSql, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    /**
     * 従業員情報を主キー検索する
     * @param id 検索する従業員ID
     * @return null または employee 従業員情報
     */
    public Employee load(Integer id) {
        SqlParameterSource param 
            = new MapSqlParameterSource().addValue("id", id);

        // NullPointerExceptionを避けるため、一度リストで受ける
        List<Employee> tmpEmployeeList 
            = namedParameterJdbcTemplate.query(loadSql, param, EMPLOYEE_ROW_MAPPER);
        
        // 主キー検索でヒットしない場合はnullを返す
        if (tmpEmployeeList == null) {
            return null;
        }

        // ヒットした場合
        return tmpEmployeeList.get(0);
    }
    
    /**
     * 従業員情報を更新する
     * idで検索し、id以外のカラムを更新する
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        SqlParameterSource param 
            = new MapSqlParameterSource().addValue("id", employee.getId());
        // 更新処理
        namedParameterJdbcTemplate.update(updateSql, param);

    }
}
