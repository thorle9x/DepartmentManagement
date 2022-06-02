package com.department.model.entity;

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
@Table(name = "CUSTOMER")
@Getter
@Setter
public class Customer extends AbstractEntity {

	private static final long serialVersionUID = -5713819885846510850L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME", length = 100, nullable = false)
	private String name;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "ADDRESS1", length = 400, nullable = false)
	private String address1;

	@Column(name = "ADDRESS2", length = 400)
	private String address2;

	@Column(name = "PHONE_NUMBER1", length = 20, nullable = false)
	private String phoneNumber1;

	@Column(name = "PHONE_NUMBER2", length = 20)
	private String phoneNumber2;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "IS_RENTER", nullable = false)
	private Boolean isRenter;

	@Column(name = "TYPE", length = 10, nullable = false)
	private String type;

	@Column(name = "REFERENCE", length = 100)
	private String reference;

	@Column(name = "NOTE")
	private String note;

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerRoom> customerRoom;

}
