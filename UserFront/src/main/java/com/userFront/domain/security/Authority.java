package com.userFront.domain.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{
	/*GrantedAuthority represents authority granted to an Authentication object*/
	private final String authority;
	
	public Authority(String authority){
		this.authority = authority;
	}
	
	@Override
	public String getAuthority(){
		return authority;
	}
}
