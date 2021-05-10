package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * RowMapper定義
	 * return employee
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER=(rs,i)->{
		Employee employee=new Employee();
		return employee;
	};
	
	/**
	 * findAllメソッド
	 * @return　employeeList
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id,name,image,gender,hire_date,mail_adderss,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDERBY hire_date DESC";
		List<Employee> employeeList
		 = template.query(sql,EMPLOYEE_ROW_MAPPER);
		if (employeeList.size() == 0) {
		return null;
		}
		return employeeList;
	}
	/**
	 * loadメソッドによるidより従業員情報取得
	 * @param id
	 * @return　employee
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,age,gender,department_id FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		Employee employee = template.queryForObject(sql, param,EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	/**
	 * updateメソッドによるid以外の従業員情報更新
	 * @param employee
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String updateSql = "UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_adderss=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=salary, characteristics=:characteristics,dependents_count=:dependentsCount WHERE id=:id";
		template.update(updateSql, param);
	}
}
