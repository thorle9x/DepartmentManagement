package com.department.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class Role extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1883621438503344203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private Long userRoleId;

	@Column(name = "IS_ACTIVE", precision = 1, scale = 0)
	private Long isActive;

	@Column(name = "ROLE_NAME", length = 50, nullable = false)
	private String roleName;

	@OneToMany(mappedBy = "role")
	@JsonIgnore
	private List<UserRole> userRoles;

}
