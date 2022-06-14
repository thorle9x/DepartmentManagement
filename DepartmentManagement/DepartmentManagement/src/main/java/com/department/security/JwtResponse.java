package com.department.security;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private final String username;
	private final long userId;
	private final String token;
	private final List<String> roles;

	public JwtResponse(long userId, String username, String token, List<String> roles) {
		this.userId = userId;
		this.username = username;
		this.token = token;
		this.roles = roles;
	}
	
	public long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return this.token;
	}

	public List<String> getRoles() {
		return roles;
	}
}
