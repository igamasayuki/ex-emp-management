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

/**
 * administratorsテーブルを操作するリポジトリ
 *
 * @author T.Araki
 */
@Repository
public class EmployeeRepository {

    /** テーブル名 */
    private static final String TABLE_NAME = "employees";

    /** employeeのロウマッパー */
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

    /** JDBCテンプレート */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 従業員一覧情報を入社日順で取得
     * 従業員が存在しない場合はサイズ0件の従業員一覧を返す
     *
     * @return 従業員一覧
     */
    public List<Employee> findAll() {

        // クエリ作成
        String sql = String.format("""
                SELECT
                    id, name, image, gender, hire_date, mail_address, zip_code
                    , address, telephone, salary, characteristics, dependents_count
                FROM
                    %s
                ORDER BY
                    hire_date DESC;
                """, TABLE_NAME);

        // 実行
        return template.query(sql, EMPLOYEE_ROW_MAPPER);

    }

    /**
     * 主キーから従業員情報を取得する
     *
     * @param id 従業員ID
     * @return 従業員情報
     */
    public Employee load(Integer id) {

        // クエリ作成
        String sql = String.format("""
                SELECT
                    id, name, image, gender, hire_date, mail_address, zip_code
                    , address, telephone, salary, characteristics, dependents_count
                FROM
                    %s
                WHERE id = :id;
                """, TABLE_NAME);

        // パラメータ作成
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        // 実行
        try {
            return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        } catch (Exception e) {
            // 情報が存在しない場合nullを返す。
            return null;
        }
    }

    /**
     * 従業員情報を変更する
     *
     * @param employee 従業員情報
     */
    public void update(Employee employee) {

        // クエリ作成
        String sql = String.format("""
                UPDATE %s
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
                    dependents_count = :dependents_count
                WHERE
                    id = :id;
                """, TABLE_NAME);

        // パラメータ作成
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        // 実行
        template.update(sql, param);
    }
}
