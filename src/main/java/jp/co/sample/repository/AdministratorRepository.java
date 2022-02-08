package jp.co.sample.repository;

import java.util.List;
<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
>>>>>>> develop
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD

import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.Employee;

@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER=
			new BeanPropertyRowMapper<>(Administrator.class);
	
	
	/**管理者情報を挿入する*/
	public void  insert(Administrator adoministrator) {
		SqlParameterSource param
			= new BeanPropertySqlParameterSource(adoministrator);
		
		String insertSql = "INSERT INTO adoministrators (id,name,mail_address,password) VALUES"
				+ "(:id,:name,:mail_address,:password)";
		template.update(insertSql, param);
	}
	
	
	/**メールアドレスとパスワードから管理者情報を取得する
	 * (1 件も存在しない場合は null を返す※)
	 * @param id ID,pass pass
	 * @return 検索された従業員情報*/
	
	
	public Administrator findByMailAddressAndPassword(String mailAddress,
			String password) {
		String Sql = "SELRCT * FROM adoministrator WHERE mail_address=:mail_address AND password=:password";
		
		SqlParameterSource param
		= new MapSqlParameterSource().addValue("mail_address",mailAddress);
		param = new MapSqlParameterSource().addValue("pass_word",password);
		
		List<Administrator> administratorList
		 = template.query(Sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
		return null;
		}
		return administratorList.get(0);
		
		
	}
}
=======
import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	private final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER =
			new BeanPropertyRowMapper<>(Administrator.class);
	@Autowired
	private NamedParameterJdbcTemplate template;

	/** 管理者をDBに登録 */
	public void insert(Administrator administrator) {
		String sql =
				"INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mailAddress,:password);";
		SqlParameterSource param =
				new MapSqlParameterSource().addValue("name", administrator.getName())
						.addValue("mailAddress", administrator.getMailAddress())
						.addValue("password", administrator.getPassword());
		template.update(sql, param);
	}

	/** 管理者をメールアドレス、パスワードをもとに1件取得 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql =
				"SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mailAddress AND password = :password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
				.addValue("password", password);
		List<Administrator> administratorList =
				template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
		} else {
			return administratorList.get(0);
		}
	}

}
>>>>>>> develop
