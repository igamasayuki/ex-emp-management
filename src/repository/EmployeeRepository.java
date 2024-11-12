import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author harasawakana
 */
@Repository

public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getjava.util.Date("hire_date"));
        employee.setmailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInteger("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInteger("dependents_count"));
        return employee;
    };

    /* 従業員⼀覧情報を入社日順で取得 */
    public List<Employee> findAll() {
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
        String insertSql = "SELECT * FROM administarters ORDER BY DESC;";
        return employeeList;
    }

    /* 従業員情報を取得 */
    public Employee load(Integer id) {
        String sql = "SELECT * FROM administarters WHERE id=:id;";
        sqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    /* 従業員情報を変更・更新 */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String updateSql = "UPDATE administarters SET name=:name, image=:image, gender=:gender, hire_date=:hireDate, mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount WHERE id=:id;";
        template.update(updateSql, param);
        return employee;
    }
}
