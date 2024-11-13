package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Employee;

/**
 * @Author:金丸天
 *             Employeeのリポジトリクラス
 */

@Repository
public class EmployeeRepository {

    /**
     * @param id=id
     * @param name=名前
     * @param image=画像
     * @param gender=性別
     * @param hireDate=誕生日
     * @param mailAddress=メールアドレス
     * @param zipCode=郵便番号
     * @param address=住所
     * @param telephone=電話番号
     * @param salary=給与
     * @param characteristics=特製
     * @param dependentsCount=扶養人数
     * @param return               DBからレコードごとにオブジェクトを生成したリストを返す
     */

    /**
     * DBから取得した情報をオブジェクトに直す
     * 
     * @param rs DB実行しているインスタンス
     * @param i  データの桁数
     * @return オブジェクトを返す
     */
    private static final RowMapper<Employee> ROW_MAPPER = (rs, i) -> {
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
     * template:DBから検索してきたオブジェクトが渡される
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * DB上にemployeeのオブジェクトを全て返す
     */
    public List<Employee> findAll() {

        String sql = "SELECT * FROM employees order by hire_date desc";

        return template.query(sql, ROW_MAPPER);

    }

    /**
     * idでemployeeのオブジェクトを1件返す
     * 
     * @param id employのID
     * @return 該当のIDのオブジェクト
     */
    public Employee load(Integer id) {

        String sql = "SELECT * FROM employees WHERE id = :id";

        SqlParameterSource source = new MapSqlParameterSource("id", id);

        return template.queryForObject(sql, source, ROW_MAPPER);

    }

    /**
     * 受けっとったEmployeeオブジェクトにて該当の主キーデータを更新
     * 
     * @param employee Employeeオブジェクト
     */
    public void update(Employee employee) {

        String sql = "UPDATE employees SET name = :name, gender = :gender, hire_date = :hireDate, mail_address = :mailAddress, zip_code = :zipCode, address = :address, telephone = :telephone, salary = :salary, characteristics = :characteristics, dependents_count = :dependentsCount WHERE id = :id";

        SqlParameterSource source = new BeanPropertySqlParameterSource(employee);

        template.update(sql, source);

    }

}
