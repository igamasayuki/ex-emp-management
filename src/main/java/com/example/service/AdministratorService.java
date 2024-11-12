package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * Administratorのサービスクラス
 */

@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * コントローラーから受け取った浮数をリポジトリに渡す
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }

    /**
     * メールアドレスとパスワードでadministrator内に該当のデータがあるか検索し、
     * 該当するレコードが存在しない場合はnullを返す
     * 
     * @param mailAddress
     * @param password
     * @return
     */
    public Administrator login(String mail, String password) {
        Administrator administrator = administratorRepository.findByMailAddressAndPassword(mail, password);
        System.out.println(administrator);
        return administrator;
    }

}
