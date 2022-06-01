package com.department.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User extends AbstractEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8868496818515249231L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "USER_ID", precision = 22, scale = 0)
  private Long userId;

  @Column(name = "USER_NAME", length = 20, nullable = false)
  private String username;

  @Column(name = "PASS_WORD", length = 4000, nullable = false)
  private String password;

  @Column(name = "FIRST_NAME", length = 100, nullable = false)
  private String firstName;

  @Column(name = "LAST_NAME", length = 100, nullable = false)
  private String lastName;
  
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AssignedUserRole> assignedUserRoles;
}
