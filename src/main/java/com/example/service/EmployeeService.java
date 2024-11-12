package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 管理者関連機能の業務処理を行うサービス
 *
 * @author T.Araki
 */
@Service
@Transactional
public class EmployeeService {

    /** employeeRepository */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全件取得
     *
     * @return 従業員情報リスト
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

    /**
     * 従業員情報の取得
     *
     * @param id 従業員ID
     * @return 従業員情報
     */
    public Employee showDetail(Integer id) {
        return employeeRepository.load(id);
    }
}
