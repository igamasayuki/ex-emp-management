package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Memberオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		// ここに結果の処理を書く
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
		employee.setDependentsCount(rs.getInt("dependents_Count"));
		return employee;
	};
	/**
	 * 全件検索を行う。
	 * @return　全従業員一覧
	 */
	public List<Employee> findAll() {
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, "
				+ "zip_code, address, telephone, salary, characteristics, dependents_count "
				+ "FROM employees "
				+ "ORDER BY hire_date DESC; ";
		List<Employee>employeeList	= template.query(sql, EMPLOYEE_ROW_MAPPER);
		System.out.println("Repository の findAll()が呼ばれました"); 
		return employeeList;
	}
	/**
	 * 主キー検索を行う
	 * @param id ID
	 * @return 検索された従業員情報
	 */
	public Employee load(Integer id) {
		String sql =
				"SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count "
				+ "FROM employees WHERE id =:id;";
		//SQL 文の「:id」プレースホルダ名に引数でもらった id を埋め込む。
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		System.out.println("Repository の load()が呼ばれました"); 
		return employee;
	}
	/**
	 * 渡した従業員情報を更新する
	 * @param employee 従業員情報
	 * @return　更新された後の従業員情報
	 */
	public void update(Employee employee){
		//プレースホルダ名と Employee オブジェクトのプロパティを関連付ける
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		String updateSql = "UPDATE employees "
					+ "SET name=:name, image=:image, gender=:gender, "
					+ 			"hire_date=:hire_date, mail_address=:mail_address, "
					+ 			"zip_code=:zip_code, address=:address, "
					+ 			"telephone=:telephone, salary=:salary, "
					+ 			"characteristics=:characteristics, dependents_count=:dependents_count " 
					+ " WHERE id=:id;";
			
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = {"id"};
		template.update(updateSql, param, keyHolder, keyColumnNames);
		employee.setId(keyHolder.getKey().intValue());
		System.out.println(keyHolder.getKey()+"が割り当てられました");
		System.out.println("Repository の save()が呼ばれました");
	}
	
}