package com.example.repository;

import java.util.List;

import javax.swing.tree.RowMapper;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {
	
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
		employee.setDependentsCount(rs.getInt("dependentscount"));
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** 従業員一覧情報を入社日順（降順）で取得
	 * @return 全従業員一覧　存在しない場合はサイズ0件の従業員一覧を返す
	 */
	
	public List<Employee> findAll(){
		String sql ="SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependentscount FROM employees";
		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}
	
	/** 主キーから従業員情報を取得
	 * @param id 検索したい従業員id
	 * @return 検索された従業員情報
	 * @exception org.springframework.dao.DataAccessException 存在しない場合は例外を発生します
	 */
	
	public Employee load(Integer id) {
		String sql ="SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependentscount FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee development = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return development;
	}
	
	/** 従業員情報を変更する
	 * 
	 */
	
	public void update(Employee employee) {
		
		String updateSql = "UPDATE employees SET name=:name"
				= ",gender=:gender"
				= ",hire_date=:hire_date"
				= ",mail_address=:mail_address"
				= ",zip_code=:zip_code"
				= ",address=:address"
				= ",telephone=:telephone"
				= ",salary=:salary"
				= ",characteristics=:characteristics"
				= ",dependentscount=:dependentscount WHERE id=:id";
		
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name", employee.getName())
				.addValue("gender", employee.getGender())
				.addValue("hire_date", employee.getHireDate())
				.addValue("mail_address", employee.getMailAddress())
				.addValue("zip_code", employee.getZipCode())
				.addValue("address", employee.getAddress())
				.addValue("telephone", employee.getTelephone())
				.addValue("salary", employee.getSalary())
				.addValue("characteristics", employee.getCharacteristics())
				.addValue("dependentscount", employee.getDependentsCount());
		
		template.update(updateSql, param);
	}

}
