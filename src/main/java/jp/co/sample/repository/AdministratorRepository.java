package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;



@Repository
public class AdministratorRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADIMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailaddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	/**
	 * 管理者情報を挿入する
	 * 
	 * @param administrator 管理者情報
	 * @return 挿入された管理者情報
	 */

	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		if (administrator.getId() == null) {
			String insertSql = "INSERT INTO administrator(name,mailaddress,password) "
					+ " VALUES(:name,:mailaddres,:password)";
			template.update(insertSql, param);
		} else {
			String updateSql = "UPDATE administrator SET name=:name,　mailaddress=:mailaddress, " + " password=:password"
					+ " WHERE　id=:id";

			template.update(updateSql, param);
		}

	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得する
	 * (1件も存在しない場合はnullを返す。
	 * @param mailaddress　MailAddress,Password　password
	 * 
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		String sql =
				 "SELECT id,name,mailaddress,password FROM administrators WHERE mailaddress=:mailaddress OR password:password";
				
				 SqlParameterSource param
				 = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",password);
				
				 
				 Administrator administrator
				 = template.queryForObject(sql, param, ADIMINISTRATOR_ROW_MAPPER);
				
				 
				 return administrator;
				 }

}


