package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Memberオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		// ここに結果の処理を書く
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailaddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	/**
	 * Administratorオブジェクトでデータをインサート
	 * @param administrator
	 */
	
	public void insert(Administrator administrator) {
		//プレースホルダ名と Administrator オブジェクトのプロパティを関連付ける
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		if (administrator.getId() == null) { 	//引数のadministratorオブジェクトがnullならINSERT文の実行
			String insertSql = "INSERT INTO administrators(name,mailaddress,password) "
							 + " VALUES(:name,:mailaddress,:password)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = {"id"};
			template.update(insertSql, param, keyHolder, keyColumnNames);
			administrator.setId(keyHolder.getKey().intValue());
			System.out.println(keyHolder.getKey()+"が割り当てられました");
		}
		else {
			System.out.println("既にそのユーザーIDは存在します");
		}
		
	}
	/**
	 * 
	 * @param mailAddress
	 * @param password
	 * @return メールアドレスとパスワードに一致したユーザー情報を返す
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password){
		String sql = "select id,name,mailaddress,password from administrators "
				+ "where mailaddress = :mailaddress, password = :password";
		// ←ここにプレースホルダにセットする処理を書く
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailaddress", mailAddress).addValue("password", password);
		
		List<Administrator> administratorList= template.query(sql, param, ADMINISTRATOR_ROW_MAPPER); 
		if (administratorList.size() == 0) {
			return null; 
		}
		return administratorList.get(0);
	}

}