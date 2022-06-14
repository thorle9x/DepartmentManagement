package com.department.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDTO extends AbstractDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3772312184925870036L;

  private long Id;

  private Long assignedUserRoleId;

  private Long isActive;

  private RoleDTO userRole;

  private UserDTO user;

}
