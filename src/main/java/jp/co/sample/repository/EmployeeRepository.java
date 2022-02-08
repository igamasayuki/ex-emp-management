package jp.co.sample.repository;

<<<<<<< HEAD
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
=======
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
>>>>>>> develop
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD

=======
>>>>>>> develop
import jp.co.sample.domain.Employee;

@Repository
public class EmployeeRepository {
<<<<<<< HEAD
	
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
		
=======
	private final RowMapper<Employee> EMPLOYEE_ROW_MAPPER =
			new BeanPropertyRowMapper<>(Employee.class);
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Employeeを全件入社日順で取得
	 *
	 * @return 全従業員リスト
	 */
	public List<Employee> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(
				"id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count ");
		sql.append("FROM employees ");
		sql.append("ORDER BY hire_date;");

		List<Employee> employeeList = template.query(sql.toString(), EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}

	/**
	 * 従業員をid検索
	 *
	 * @param id ID
	 * @return 従業員1件
	 */
	public Employee load(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(
				"id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count ");
		sql.append("FROM employees ");
		sql.append("WHERE id = :id;");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql.toString(), param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}

	/**
	 * 従業員更新
	 *
	 * @param employee 従業員情報
	 * @return 更新された従業員の情報
	 */
	public void update(Employee employee) {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE employees SET ");
		sql.append("name = :name,");
		sql.append("image = :image,");
		sql.append("gender = :gender,");
		sql.append("hire_date = :hireDate,");
		sql.append("mail_address = :mailAddress,");
		sql.append("zip_code = :zipCode,");
		sql.append("telephone = :telephone,");
		sql.append("salary = :salary,");
		sql.append("characteristics = :characteristics,");
		sql.append("dependents_count = :dependentsCount ");
		sql.append("WHERE id = :id;");
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql.toString(), param);

>>>>>>> develop
	}
}
