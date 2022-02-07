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

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER=(rs,i)->{
		Administrator adoministrator = new Administrator();
		adoministrator.setId(rs.getInt("id"));
		adoministrator.setName(rs.getString("name"));
		adoministrator.setMail(rs.getString("mail"));
		adoministrator.setPass(rs.getString("pass"));
		return adoministrator;
	};
	
	/**管理者情報を挿入する*/
	public void  insert(Administrator adoministrator) {
		SqlParameterSource param
			= new BeanPropertySqlParameterSource(adoministrator);
		
		String insertSql = "INSERT INTO adoministrator(id,name,mail,pass) VALUES"
				+ "(:id,:name,:mail,:pass)";
		template.update(insertSql, param);
	}
	
	
	/**メールアドレスとパスワードから管理者情報を取得する
	 * (1 件も存在しない場合は null を返す※)
	 * @param id ID,pass pass
	 * @return 検索された従業員情報*/
	
	
	public Administrator findByMailAddressAndPassword(String mail,
			String pass) {
		String Sql = "SELRCT * FROM adoministrator WHERE mail=:mail AND pass=:pass;";
		
		SqlParameterSource param
		= new MapSqlParameterSource().addValue("mail",mail);
		param = new MapSqlParameterSource().addValue("pass",pass);
		
		List<Administrator> administratorList
		 = template.query(Sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
		return null;
		}
		return administratorList.get(0);
		
		
	}
}
