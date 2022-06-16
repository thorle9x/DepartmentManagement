package com.department.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends AbstractDTO {

	private static final long serialVersionUID = -1272164913553696913L;

	private Long userId;

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private List<String> userRoles;

}
