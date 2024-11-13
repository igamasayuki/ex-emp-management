package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**@author harasawa */

/**
 * employeesテーブルを 操作するリポジトリ 
 */

@Service
@Transactional

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /** 従業員一覧を表示 */
    public List<Employee> showList() { 
        return employeeRepository.findAll(); 
    }

}
