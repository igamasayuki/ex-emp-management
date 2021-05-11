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
		administrator.setMailAddress(rs.getString("mail_address"));
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
			String insertSql = "INSERT INTO administrators(name,mail_address,password) "
					+ " VALUES(:name,:mailAddres,:password)";
			template.update(insertSql, param);
		} else {
			String updateSql = "UPDATE administrators SET name=:name,　mail_address=:mailAddress, "
					+ " password=:password" + " WHERE　id=:id";

			template.update(updateSql, param);
		}

	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得する (1件も存在しない場合はnullを返す。
	 * 
	 * @param mailaddress MailAddress,Password password
	 * 
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		
		try {
			SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
					.addValue("password", password);

			String sql = "SELECT id,name,mail_address,password FROM administrators"
					+ " WHERE mail_address=:mailAddress AND password=:password";

			Administrator administrator = template.queryForObject(sql, param, ADIMINISTRATOR_ROW_MAPPER);
			return administrator;
		} catch (Exception e) {
			return null;
		}
		
	}

}
