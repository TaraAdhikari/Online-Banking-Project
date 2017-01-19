package com.userFront.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userFront.domain.PrimaryTransaction;
import com.userFront.domain.SavingsTransaction;
import com.userFront.domain.User;
import com.userFront.service.TransactionService;
import com.userFront.service.UserService;

@RestController//when we are returning some instances, spring will help us to de-serialize by-default and pass that into the JSON format
@RequestMapping("/api")
@PreAuthorize("hasRole('ADMIN')")//if we use hasrole method then spring will automatically use as ROLE_ADMIN
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public List<User> userList(){
		return userService.findUserList();
	}
	
	@RequestMapping(value = "/user/primary/transaction", method = RequestMethod.GET)
	public List<PrimaryTransaction> getPrimaryTransactionList(@RequestParam("username") String username){
		return transactionService.findPrimaryTransactionList(username);
	}
	
	@RequestMapping(value = "/user/savings/transaction", method = RequestMethod.GET)
	public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("username") String username){
		return transactionService.findsavingsTransactionList(username);
	}
	
	@RequestMapping(value = "/user/{username}/enable")
	public void enableUser(@PathVariable("username") String username){
		userService.enableUser(username);
	}

	@RequestMapping(value = "/user/{username}/disable")
	public void disableUser(@PathVariable("username") String username){
		userService.disableUser(username);
	}
	

}
