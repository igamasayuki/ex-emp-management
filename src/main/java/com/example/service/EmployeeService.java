package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全件取得する
     * return 全従業員リスト
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * 従業員情報を主キー検索で取得する
     * @param id 従業員ID
     * @return IDに一致する従業員情報
     */
    public Employee load(Integer id) {
        return employeeRepository.load(id);
    }

    /**
     * IDに一致する従業員情報を更新する
     * id以外のカラムを更新する
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }
}
