package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author M.aoki
 * 
 */
@Service
@Transactional
public class AdministratorService {

  @Autowired
  private AdministratorRepository administratorRepository;

  /**
   * 管理者情報登録をする業務処理を行う.
   * 
   * @param administrator
   */
  public void insert(Administrator administrator) {
    administratorRepository.insert(administrator);
  }

  /**
   * ログインをする業務処理を行う.
   * 
   * @param mailAddress
   * @param password
   * @return administrator
   */
  public Administrator login(String mailAddress, String password) {
    return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
  }
}
