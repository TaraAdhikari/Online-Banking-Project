package com.userFront.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.userFront.service.UserServiceImpl.UserSecurityService;

@Configuration//spring will know this is a configuration class
@EnableWebSecurity// it will enable the spring security
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {//WebSecurityConfigurerAdapter, it will help us configure our security
	
	/*@Autowired
	private Environment env;*/
	
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private static final String SALT = "salt";
	
	/*Salt should be protected carefully, it will help us to encode our password.
	It will be huge disadvantage if we save our password in database without encoding, so for security reason. salt help us to 
	generate strings of password every-time differently. So, it is use as seed here.*/ 
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		
		// this is password encrypting method.and we are using @Bean to register this method as a bean in the spring container.
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/webjars/**",//this is a string array that is a public matchers. This is a list of specific paths that we will like  
			"/css/**",//to access publicly without spring security protection and thats because those are some of the files that
			"/js/**",//should be publicly available before even doing security verification.
			"/images/**",
			"/",
			"/about/**",
			"/contact/**",
			"/error/**",
			"/console/**",
			"/signup/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		/*	http
				.authorizeRequests()// whenever it matches with any of the public matchers in the list, their permission will be granted otherwise any request that dosenot match with the public matchers need to be authenticated.
//				.antMatchers("/**")
				.antMatchers(PUBLIC_MATCHERS)
				.permitAll().anyRequest().authenticated();
		
		http
				.csrf().disable().cors().disable()//this will disable the protection of csrf and cors attack so that we donot face cors reason issue during the development  
				.formLogin().failureUrl("/index?error").defaultSuccessUrl("/userFront").loginPage("/index").permitAll()//
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("remember-me").permitAll()
				.and()
				.rememberMe();*/
		
		 http
         .authorizeRequests().
//         antMatchers("/**").
         antMatchers(PUBLIC_MATCHERS).
         permitAll().anyRequest().authenticated();

 http
         .csrf().disable().cors().disable()  /*csrf is bydefault enabled and we have to disable it for spring security*/
         .formLogin().failureUrl("/index?error").defaultSuccessUrl("/userFront").loginPage("/index").permitAll()
         .and()
         .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("remember-me").permitAll()
         .and()
         .rememberMe();
				
	}
	
	@Autowired
	public void configurationGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
