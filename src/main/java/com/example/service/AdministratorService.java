package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者情報を操作するサービス.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorRepository administratorRepository;

    /**
     * メールアドレスとパスワードを基に、管理者としてログインする.
     *
     * @param mailAddress メールアドレス
     * @param password    パスワード
     * @return 管理者の情報
     */
    public Administrator login(String mailAddress, String password) {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }

    /**
     * 管理者情報を登録する.
     *
     * @param administrator 登録する管理者情報
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }
}
