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

	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Administrator administrator) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
