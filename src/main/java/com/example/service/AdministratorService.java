package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * administratorsテーブルを操作するサービスクラス
 */
@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository repository;

    /**
     * 管理者情報をリポジトリを通じて追加
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        repository.insert(administrator);
    }

    /**
     * メールアドレスとパスワードを受け取り、リポジトリを通じて管理者情報を表示
     * @param mailAddress
     * @param password
     * @return 検索された管理者情報
     */
    public Administrator login(String mailAddress, String password) {
        Administrator administrator = repository.findByMailAddressAndPassword(mailAddress, password);
        return administrator;
    }
}
