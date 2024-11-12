package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス
 *
 * @author T.Araki
 */
@Service
@Transactional
public class AdministratorService {

    /** administratorRepository */
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者情報を挿入する
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {

        administratorRepository.insert(administrator);
    }

}
