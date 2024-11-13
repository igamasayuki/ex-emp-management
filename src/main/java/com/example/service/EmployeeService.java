package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員関連情報の業務処理を行うサービス.
 * 
 * @author M.aoki
 */
@Service
@Transactional
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * 従業員情報を全権取得する
   * 
   * @return 全従業員情報
   */
  public List<Employee> showList() {
    return employeeRepository.findAll();
  }

  /**
   * 従業員情報を取得する.
   * 
   * @param id 従業員ID
   * @return 主キー検索した結果の従業員
   */
  public Employee showDetail(Integer id) {
    return employeeRepository.load(id);
  }

  /**
   * 従業員情報を更新する.
   * 
   * @param employee 更新したい従業員情報
   */
  public void update(Employee employee) {
    employeeRepository.update(employee);
  }
}
