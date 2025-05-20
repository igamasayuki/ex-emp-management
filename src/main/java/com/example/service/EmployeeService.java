package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 従業員操作用のサービス.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    /**
     * 従業員一覧を取得する.
     *
     * @return 従業員一覧情報
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

    /**
     * IDに一致する従業員を取得する.
     *
     * @param id 従業員ID
     * @return 従業員情報
     */
    public Employee showDetail(Integer id) {
        return employeeRepository.findById(id);
    }
}
