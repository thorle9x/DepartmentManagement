package com.department.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "ASSIGNED_USER_ROLES")
@Getter
@Setter
public class AssignedUserRole extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 6772973910066438267L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;


  @Column(name = "ASSIGNED_USER_ROLE_ID", precision = 22, scale = 0)
  private Long assignedUserRoleId;

  @Column(name = "IS_ACTIVE")
  private Long isActive;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
  private User user;

  @ManyToOne
  @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "USER_ROLE_ID")
  private UserRole userRole;

}
