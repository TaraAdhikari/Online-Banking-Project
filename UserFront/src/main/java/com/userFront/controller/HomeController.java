package com.userFront.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userFront.Dao.RoleDao;
import com.userFront.domain.PrimaryAccount;
import com.userFront.domain.SavingsAccount;
import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;
import com.userFront.service.UserService;

@Controller  /*this is to register a class to a controller. with this , this class will be registered as a bean in
 the spring container automatically. from this, spring knows that this bean exist and this will shows the corresponding path
 that resign in this class to our spring system*/
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleDao roleDao;
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/index";
	}
	/*either we hit "/"(root path) or /index, then they both gonna return to index as string which inturn will be the index.html file
	 * and we spring automatically recognize index as index.html is because we have added dependency of thymeleaf in our pom.xml so that 
	 * it will automatically recognize the template which have extension as .html */
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	/*here we are mapping the request to /signup path and the method is get . the method in path with url will always be GET method
	 * here we have define model. model will bind the user we have initialize to the new "user" and bind the user object to the variable 
	 * "user" and return to the "signup" html. and then we will find the user variable defined within the context of that html file
	 * now we can access the firstName, lastName and phone of the user object. After we will all value in form and hit the submitt button then 
	 * it will direct to the same /signup action but of the post method instead of get*/
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model){
		User user= new User();
		
		model.addAttribute("user", user);
		
		return "signup";
	}
	/*since the value name is same we have changed the method title to signupPost otherwise it will make contradiction
	 * and it will give a compiler error. @ModelAttribute will help us to retrieve a variable called user from the context of html that has 
	 * just been submitted and keep the value to user object instance we have created here*/
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model){
		
		if(userService.checkUserExists(user.getUsername(), user.getEmail())){
			
			if(userService.checkEmailExists(user.getEmail())){
				model.addAttribute("emailExists",true);
			}
			
			if(userService.checkUsernameExists(user.getUsername())){
				model.addAttribute("usernameExists", true);
			}
			
			return "signup";
			
		}else{
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

			userService.createUser(user, userRoles);

			return "redirect:/";
		}
	}
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model){
	/*Principal in case of spring security context is person who has logged in into userFront. Here we can use principal.getName() to get the principal username
	 * specifically. And userService.findByUsername() to retrieve the user instance who has log in in the system.Then, we will define a primaryAccount instance 
	 * which will be assigning to the users and retrieve back the primary and savings account.*/
		User user = userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();
		
		model.addAttribute("primaryAccount",primaryAccount);
		model.addAttribute("savingsAccount",savingsAccount);
		
		return "userFront";//In this case, in the userFront html, we will have the primaryAccount and savingsAccount, two instances available to be used.
	}
}
	
