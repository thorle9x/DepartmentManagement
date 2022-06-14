package com.department.model.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO extends AbstractDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -904786520437355295L;

  private Long id;
  
  private Long userRoleId;

  private Long isActive;

  private String roleName;
}
