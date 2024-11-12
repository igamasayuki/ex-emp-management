package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/**
 * @Author:金丸天
 *             Administratorのリポジトリクラス
 */

@Repository
public class AdministratorRepository {

    /**
     * @param
     * id=ID
     *              name=名前
     *              mailAddress=メールアドレス
     *              password=パスワード
     *              return DBからレコードごとにオブジェクトを生成したリストを返す
     */

    private static final RowMapper<Administrator> Row_Mapper = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mailAddress"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };
    /**
     * template:DBから検索してきたオブジェクトが渡される
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * DB上にadministratorのIDが存在しなければ挿入
     * 存在すれば更新を行う
     */

    public void insert(Administrator administrator) {

        SqlParameterSource source = new BeanPropertySqlParameterSource(administrator);

        if (administrator.getId() == null) {

            String sql = "INSERT INTO administrators (name, mailAddress, password) VALUES (:name, :mailAddress, :password)";

            template.update(sql, source);

        } else {

            String sql = "UPDATE administrators SET name = :name, mailAddress = :mailAddress, password = :password WHERE id = :id";

            template.update(sql, source);

        }
    }

    // メールアドレスとパスワードでadministrator内に該当のデータがあるか検索し、
    // 該当するレコードが存在しない場合はnullを返す
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

        String sql = "SELECT * FROM administrators WHERE mailAddress = :mailAddress AND password = :password";

        SqlParameterSource param = new MapSqlParameterSource();

        Administrator administrator = template.queryForObject(sql, param, Row_Mapper);

        if (administrator != null) {

            return null;
        } else {

            return administrator;

        }

    }
}
