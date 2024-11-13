package com.example.service;

/**
 * @author harasawakana
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.domain.Administrator;
import com.example.domain.Employee;
import com.example.repository.AdministratorRepository;


@Service
@Transactional

public class AdministratorService {
    @Autowired
    private AdministratorRepository repository;

    /** 管理者情報を操作する */
    public Administrator login(String mailAddress,  String password) {
        return repository.findByMailAddressAndPassword(mailAddress, password);
    }

    /** 管理者情報の登録処理*/
    public void insert(Administrator administrator) {
        repository.insert(administrator);
    }

    // public List<Employee> findAll() {
    //     return repository.findAll();
    // }

    // public Employee load(Integer id) {
    //     return repository.load(id);
    // }

    // public Employee update() {
    //     return repository.update();
    // }
}
