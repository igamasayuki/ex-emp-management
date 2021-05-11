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
	
	//管理者情報を挿入する。
	//administratorRepositoryのinsert()メソッドを呼ぶ
	public void insert(Administrator administrator) {
		AdministratorRepository administratorRepository = new AdministratorRepository();
		administratorRepository.insert(administrator);
	}
	
	//ログイン処理をする。
	public Administrator login(String mailAddress,String password) {
		//administratorRepositoryのfindByMailAddressAndPassword ()メソッドを呼ぶ
		Administrator administrator = administratorRepository.findByMailAddressAndPassword(mailAddress, password);
		//戻ってきた管理者情報をそのまま呼び出し元に返す。
		return administrator;
	}
}
