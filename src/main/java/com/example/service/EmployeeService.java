package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * @author 金丸天
 *         Employeeのサービスクラス
 */
@Service
@Transactional
public class EmployeeService {
    /**
     * リポジトリのオブジェクトを注入
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * EmployeeRepositoryからリポジトリの全件検索の呼び出し
     * 
     * @return すべての従業員のリスト
     */
    public List<Employee> showList() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;

    }

    /**
     * EmployeeRepositoryから対象のidのオブジェクトを取得する
     * 
     * @param id
     * @return
     */
    public Employee showDetail(Integer id) {
        return employeeRepository.load(id);
    }

    /**
     * @param employee
     *                 EmployeeRepositoryから対象のemployeeのオブジェクトを更新する
     */
    public void update(Employee employee) {
        employeeRepository.update(employee);

    }

}
