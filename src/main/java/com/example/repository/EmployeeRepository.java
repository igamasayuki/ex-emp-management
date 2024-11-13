package com.example.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
        } catch (IncorrectResultSizeDataAccessException e) {
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
                    dependents_count = :dependentsCount
                WHERE
                    id = :id;
                """, TABLE_NAME);

        // パラメータ作成
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        // 実行
        template.update(sql, param);
    }

    /**
     * 従業員情報を名前で曖昧検索
     *
     * @param name 名前
     * @return 従業員情報リスト
     */
    public List<Employee> findByName(String name) {

        String sql = String.format("""
                SELECT
                    ID,
                    NAME,
                    IMAGE,
                    GENDER,
                    HIRE_DATE,
                    MAIL_ADDRESS,
                    ZIP_CODE,
                    ADDRESS,
                    TELEPHONE,
                    SALARY,
                    CHARACTERISTICS,
                    DEPENDENTS_COUNT
                FROM
                    %s
                WHERE
                    NAME LIKE :name
                ORDER BY
                    HIRE_DATE DESC;
                """, TABLE_NAME);

        SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

        List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);

        return employeeList;
    }

    /**
     * 従業員情報を入社日の期間で検索
     *
     * @param started 入社日(開始期間)
     * @param ended   入社日(終了期間)
     * @return 従業員情報リスト
     */
    public List<Employee> findByHireDate(String started, String ended) {

        StringBuilder sql = new StringBuilder();
        sql.append(String.format("""
                SELECT
                    ID,
                    NAME,
                    IMAGE,
                    GENDER,
                    HIRE_DATE,
                    MAIL_ADDRESS,
                    ZIP_CODE,
                    ADDRESS,
                    TELEPHONE,
                    SALARY,
                    CHARACTERISTICS,
                    DEPENDENTS_COUNT
                FROM
                    %s
                WHERE
                    HIRE_DATE
                """, TABLE_NAME));

        SqlParameterSource param = null;

        if (!(started.isEmpty()) && !(ended.isEmpty())) {
            sql.append(" BETWEEN :started AND :ended ");
            param = new MapSqlParameterSource().addValue("started",
                    Date.valueOf(started)).addValue("ended", Date.valueOf(ended));
        } else if (!(started.isEmpty())) {
            sql.append(" >= :started ");
            param = new MapSqlParameterSource().addValue("started", Date.valueOf(started));
        } else {
            sql.append(" <= :ended ");
            param = new MapSqlParameterSource().addValue("ended", Date.valueOf(ended));
        }

        sql.append("ORDER BY HIRE_DATE DESC;");

        return template.query(sql.toString(), param, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 従業員情報を入社日の名前と期間で検索
     *
     * @param name    名前
     * @param started 入社日(開始期間)
     * @param ended   入社日(終了期間)
     * @return 従業員情報リスト
     */
    public List<Employee> findByNameHireDate(String name, String started, String ended) {

        StringBuilder sql = new StringBuilder();
        sql.append(String.format("""
                SELECT
                    ID,
                    NAME,
                    IMAGE,
                    GENDER,
                    HIRE_DATE,
                    MAIL_ADDRESS,
                    ZIP_CODE,
                    ADDRESS,
                    TELEPHONE,
                    SALARY,
                    CHARACTERISTICS,
                    DEPENDENTS_COUNT
                FROM
                    %s
                WHERE
                    NAME LIKE :name
                AND
                    HIRE_DATE
                """, TABLE_NAME));

        SqlParameterSource param = null;

        String paramName = "%" + name + "%";

        if (!(started.isEmpty()) && !(ended.isEmpty())) {
            sql.append(" BETWEEN :started AND :ended ");
            param = new MapSqlParameterSource().addValue("name", paramName).addValue("started",
                    Date.valueOf(started)).addValue("ended", Date.valueOf(ended));
        } else if (!(started.isEmpty())) {
            sql.append(" >= :started ");
            param = new MapSqlParameterSource().addValue("name", paramName).addValue("started", Date.valueOf(started));
        } else {
            sql.append(" <= :ended ");
            param = new MapSqlParameterSource().addValue("name", paramName).addValue("ended", Date.valueOf(ended));
        }

        sql.append("ORDER BY HIRE_DATE DESC;");

        return template.query(sql.toString(), param, EMPLOYEE_ROW_MAPPER);

    }
}
