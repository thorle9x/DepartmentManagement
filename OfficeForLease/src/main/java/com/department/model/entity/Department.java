package com.department.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "DEPARTMENT")
@Getter
@Setter
public class Department extends AbstractEntity implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 689010845137471596L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME", length = 100, nullable = false)
	private String name;

	@Column(name = "OWNER", length = 100, nullable = false)
	private String owner;

	@Column(name = "ADDRESS", length = 100, nullable = false)
	private String address;

	@Column(name = "TOTAL_ROOM")
	private Long totalRoom;

	@Column(name = "INVESMENT")
	private Float invesment;

	@Column(name = "START_DATE", nullable = false)
	private Timestamp startDate;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Room> rooms;

}
