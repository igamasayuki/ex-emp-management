package jp.co.sample.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {

	private final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(Administrator.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/** 管理者情報を登録する*/

	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mailAddress,:password);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", administrator.getName())
				.addValue("mailAddress", administrator.getMailAddress())
				.addValue("password", administrator.getPassword());
		template.update(sql, param);
	}

	/**メールアドレスとパスワードから管理者情報を取得*/

	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mailAddress AND password = :password;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
				.addValue("password",password);
		
		List<Administrator> administratorList 
			= template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		
		if (administratorList.size() == 0) {
			return null;
		} else {
			return administratorList.get(0);
		}
	}

}