package com.userFront.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userFront.domain.User;


public interface UserDao extends CrudRepository<User, Long> {//CrudRepository represents create,read,update,delete(CRUD) operations

	User findByUsername(String username);
	User findByEmail(String email);
	List<User> findAll();
}
