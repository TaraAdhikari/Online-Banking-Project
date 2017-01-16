package com.userFront.Dao;

import org.springframework.data.repository.CrudRepository;

import com.userFront.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long>{
	
	 PrimaryAccount findByAccountNumber (int accountNumber);
		// TODO Auto-generated method stub
		
}

