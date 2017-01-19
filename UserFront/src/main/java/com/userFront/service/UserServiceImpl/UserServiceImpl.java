package com.userFront.service.UserServiceImpl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userFront.Dao.RoleDao;
import com.userFront.Dao.UserDao;
import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;
import com.userFront.service.AccountService;
import com.userFront.service.UserService;



@Service//to register this service class in bean 
@Transactional/*//with this we will be able to do some transactions that are provided by spring hibernate and database. without this some of the transactions that have to be supported cannot be supported. 
. we bring it to the class level so that within the class everything will be supportable*/	
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	
	@Autowired //based on the idea of DI. when you need some kind of instances, you ask for it, and then spring boot or the container will just give it to you based on what you need
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountService accountService;
	
	public void save(User user){
		userDao.save(user);
	}
	
	@Override
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	
	@Override
	public User findByEmail(String email){
		return userDao.findByEmail(email);
	}
	
	public User createUser(User user, Set<UserRole> userRoles){
		User localUser = userDao.findByUsername(user.getUsername());
		
		if(localUser!=null){
			LOG.info("User with usernme{} already exist. Nothing will be done.", user.getUsername());
		}else{
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			
			for(UserRole ur : userRoles){
				roleDao.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			user.setPrimaryAccount(accountService.createPrimaryAccount());
			user.setSavingsAccount(accountService.createSavingsAccount());
			
			localUser = userDao.save(user);
		}
		return localUser;
	}
	
	
	
	
	
	
	public boolean checkUserExists(String username, String email){
		if(checkUsernameExists(username) || checkEmailExists(username)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkEmailExists(String email) {
		// TODO Auto-generated method stub
		if(null!= findByEmail(email)){
			return true;
		}
		return false;
	}

	public boolean checkUsernameExists(String username) {
		// TODO Auto-generated method stub
		if(null != findByUsername(username)){
			return true;
		}
			return false;
	}
	
	public void saveUser(User user){
		userDao.save(user);
	}
	
	public List<User> findUserList(){
		return userDao.findAll();
	}
	
	public void enableUser(String username){
		User user = findByUsername(username);
		user.setEnabled(true);
		userDao.save(user);
	}

	public void disableUser(String username){
		User user = findByUsername(username);
		user.setEnabled(false);
		System.out.println(user.isEnabled());
		userDao.save(user);
		System.out.println(username + "is disabled");
	}
	

}
