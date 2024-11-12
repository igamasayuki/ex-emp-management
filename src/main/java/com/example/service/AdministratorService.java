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

    public void insert(Administrator administrator) {
        repository.insert(administrator);
    }

    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        return findByMailAddressAndPassword(mailAddress, password);
    }
}
