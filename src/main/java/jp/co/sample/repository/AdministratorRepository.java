package jp.co.sample.repository;

import java.util.List;


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
	
	/**
	 * RowMapper定義
	 * return administrator
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER=(rs,i)->{
		Administrator administrator=new Administrator();
		return administrator;
	};
	
	/**
	 * insertメソッドによる管理者情報挿入
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param=new BeanPropertySqlParameterSource(administrator);
		if(administrator.getId()==null) {
			String insertsql = "INSERT INTO administrators(name,mail_address,password)"+"VALUES(:name,:mailAddress,:password)";
			template.update(insertsql,param);
		}
	}
	/**
	 * findbyメソッドによるメールアドレス・パスワード検索
	 * @param mailAddress
	 * @param password
	 * @return
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password){
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress and password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress",mailAddress).addValue("password",password);
		List<Administrator> administratorList
		 = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
		return null;
		}
		return administratorList.get(0);
	}
}