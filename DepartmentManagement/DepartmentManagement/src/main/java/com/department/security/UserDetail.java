package com.department.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.department.model.dto.UserRoleDTO;

public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private List<UserRoleDTO> assignedUserRoles;

	public UserDetail(String username, String password, List<UserRoleDTO> assignedUserRoles) {
		this.username = username;
		this.password = password;
		this.assignedUserRoles = assignedUserRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		assignedUserRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getRoleName())));
		return authorities;
	}

	public List<UserRoleDTO> getAssignedUserRoles() {
		return assignedUserRoles;
	}

	public void setAssignedUserRoles(List<UserRoleDTO> assignedUserRoles) {
		this.assignedUserRoles = assignedUserRoles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
