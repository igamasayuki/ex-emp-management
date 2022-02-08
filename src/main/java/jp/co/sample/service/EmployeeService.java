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
	private EmployeeRepository repository;

	/**
	 * 従業リストを取得
	 * @return 従業員リスト
	 */
	public List<Employee> showList() {
		return repository.findAll();
	}

	/**
	 * 従業員詳細を返す
	 * @param id ID
	 * @return 従業員情報
	 */
	public Employee showDetail(Integer id) {
		return repository.load(id);
	}

	/**
	 * 従業員情報を更新
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
		repository.update(employee);
	}
}

