package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository repository;

	/**
	 * 管理者を登録
	 *
	 * @param administrator 管理者
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}

	/**
	 * 管理者をメールアドレス、パスワードをもとに1件取得
	 *
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 管理者情報
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		return repository.findByMailAddressAndPassword(mailAddress, password);
	}
}