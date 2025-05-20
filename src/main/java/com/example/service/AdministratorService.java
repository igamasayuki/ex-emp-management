package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    /**
     * 管理者情報を登録する
     *
     * @param administrator 登録する管理者情報
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }
}
