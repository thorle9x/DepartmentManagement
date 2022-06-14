package com.department.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "USER_ROLE")
@Getter
@Setter
public class UserRole extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 6772973910066438267L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "IS_ACTIVE")
	private Long isActive;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Role role;

}
