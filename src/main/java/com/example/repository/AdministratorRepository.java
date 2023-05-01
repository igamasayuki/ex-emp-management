package com.example.repository;

import java.util.List;

import javax.swing.tree.RowMapper;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** 管理者情報を取得
	* @param administrator 管理者情報
	*/
	
	public void insert (Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "insert into administrators(name,mail_address,password)values(:name,:mailAddress,:password);";
		template.update(sql, param);
	}
	
	/** メールアドレスとパスワードから管理者情報を取得
	* @param mailAddress
	* @param password
	* @return 管理者情報　存在しない場合はnullを返す
	*/
	
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "select id,name,mail_address,password from administrators where mailaddress = '" + mailaddress
				+ "' and password = '" + password + "'";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administratorList.size() == 0) {
			return null;
		}
		
		return administratorList.get(0);
	}

}
