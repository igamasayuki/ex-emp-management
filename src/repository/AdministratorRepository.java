import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * @author harasawakana
 */
@Repository

public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        Administrator administrator = new Administarator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };
    
    /*管理者情報を入力 */
    public void insert(Administarter administarter) {
        sqlParameterSource param = new AdministratorRepository(administarter);
        String insertSql = "INSERT INTO administarters(id,name,mailAddress,password)" + "VALUES(:id,:name,:mail_address,:password)";
    }
    
    /*メールアドレスを取得 */
    public Administarator findByMailAddressAndPassword(String mailAddress, String password) {
        List<Administrator> administratorList 
        = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER); 
        if (administratorList.size() == 0) { 
        return null; 
        } 
        return administratorList.get(0); 
    }
}
