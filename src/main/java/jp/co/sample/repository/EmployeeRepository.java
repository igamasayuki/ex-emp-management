package jp.co.sample.repository;

import java.util.Date;
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

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER=(rs,i)->{
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("giredate"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependentsCount"));
		return employee;
	};
	
	
	/**主キーから従業員情報を取得する
	 * (従業員が存在しない場合は Spring が⾃動的に例外を発⽣します)。
	 * @param <SqlparameterSource>
	 */
	
	public Employee load(Integer id) {
		String sql="SELECT * FROM emoloyees WHERE id=:id";
		
		SqlParameterSource param =
				new MapSqlParameterSource().addValue("id",id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
	}
	
	/**従業員⼀覧情報を⼊社⽇順(降順)で取得する
	 * (従業員が存在しない場合はサイズ 0件の従業員⼀覧を返す)
	 */
	
	public List<Employee> findAll(){
		String sql = "SELECT * FROM employees ORDER BY hireDate DESC";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER); 
		return employeeList;
	}

	public void update(Employee employee) {
		SqlParameterSource param
		= new BeanPropertySqlParameterSource(employee);
	
	String insertSql = "INSERT INTO employees (id,name,image,gender,hireDate,mailAddress,zipCode,address,telephone,salary,characteristics,dependentsCount) VALUES"
			+ "(:id,:name,:image,:gender,:hireDate,:mailAddress,:zipCode,:address,:telephone,:salary,:characteristics,:dependentsCount)";
	template.update(insertSql, param);
		
	}
}
