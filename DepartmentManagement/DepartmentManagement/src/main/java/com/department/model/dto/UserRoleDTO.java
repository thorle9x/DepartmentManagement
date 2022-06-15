package com.department.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRoleDTO {
	@JsonIgnore
	private Long id;

	private Long isActive;

	private RoleDTO role;

	@JsonIgnore
	private UserDTO user;

}
