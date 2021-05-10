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
	private AdministratorRepository administratorRepository;
	
	/**
	 * administratorRepositoryのinsert()メソッドを呼び出す
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	/**
	 * アドレスとパスワードより検索
	 * @param mailAddress
	 * @param password
	 * @return administrator
	 */
	public Administrator login(String mailAddress,String password) {
		Administrator administrator=new Administrator();
		administrator=administratorRepository.findByMailAddressAndPassword(mailAddress,password);
		return administrator;
	}
}