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

}
