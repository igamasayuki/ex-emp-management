package com.example.repository;

import com.example.domain.Administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, index) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	private static final String INSERT_SQL = """
			INSERT INTO
				administrators(
					id
					, name
					, mail_address
					, password
				)
			VALUES (
				:id
				, :name
				, :mail_address
				, :password
			);
			""";

	private static final String FIND_BY_MAILADDRESS_AND_PASSWORD = """
			SELECT
				id
				, name
				, mail_address
				, password
			FROM
				administrators
			WHERE
				mail_address = :mail_address
				AND
				password = :password;
			""";

	public void insert(Administrator administrator) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", administrator.getId())
				.addValue("name", administrator.getName())
				.addValue("mail_address", administrator.getMailAddress())
				.addValue("password", administrator.getPassword());
		template.query(INSERT_SQL, params, ADMINISTRATOR_ROW_MAPPER);
	}

	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("mailAddress", mailAddress)
				.addValue("password", password);
		List<Administrator> administrators = template.query(FIND_BY_MAILADDRESS_AND_PASSWORD, params,
				ADMINISTRATOR_ROW_MAPPER);
		if (administrators.size() == 0) {
			return null;
		}
		return administrators.get(0);
	}
}
