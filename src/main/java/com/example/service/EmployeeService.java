package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * employeeRepositoryからfindAllメソッドを呼び出しリターンする。
     *
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

}
