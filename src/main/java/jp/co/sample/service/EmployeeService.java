package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得
	 * @return employeeList
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 従業員情報の取得
	 * @param id
	 * @return employeeRepository.load()の情報
	 */
	public Employee showDetail(Integer id){
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員情報の更新
	 * @param employee
	 */
	public void update(Employee employee){
		employeeRepository.update(employee);
	}
}