package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administorator;

/**
 * administratorsテーブルを操作するリポジトリ
 *
 * @author T.Araki
 */
@Repository
public class AdministratorRepository {

    /** テーブル名 */
    private static final String TABLE_NAME = "administrators";

    /** administoratorのロウマッパー */
    private static final RowMapper<Administorator> ADMINISTORATOR_ROW_MAPPER = new BeanPropertyRowMapper<>(
            Administorator.class);

    /** JDBCテンプレート */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 管理者情報を挿入する
     *
     * @param administorator 管理者情報
     */
    public void insert(Administorator administorator) {

        // クエリ作成
        String sql = String.format("INSERT INTO %s(name, mailAddress, password) VALUES(:name, :mailAddress, :password)",
                TABLE_NAME);

        // パラメータ作成
        SqlParameterSource param = new BeanPropertySqlParameterSource(administorator);

        // 実行
        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する。
     * 1件も存在しない場合はnullを返す。
     *
     * @param mailAddress メールアドレス（入力値）
     * @param password    パスワード（入力値）
     * @return 管理者情報
     */
    public Administorator findByMailAddressPassword(String mailAddress, String password) {

        // クエリ作成
        String sql = String.format("""
                    SELECT id, name, mailAddress, password FROM %s
                    WHERE mailAddress = :mailAddress AND password = :password
                """, TABLE_NAME);

        // パラメータ作成
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);

        // 実行
        try {
            return template.queryForObject(sql, param, ADMINISTORATOR_ROW_MAPPER);
        } catch (Exception e) {
            // 情報が存在しない場合nullを返す。
            return null;
        }
    }
}
