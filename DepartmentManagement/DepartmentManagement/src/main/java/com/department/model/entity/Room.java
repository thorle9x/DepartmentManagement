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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROOM")
@Getter
@Setter
public class Room extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 3013027666893861020L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CODE", length = 20, nullable = false)
	private String code;

	@Column(name = "NAME", length = 100, nullable = false)
	private String name;

	@Column(name = "ADDRESS", length = 100, nullable = false)
	private String address;

	@Column(name = "IMAGES")
	private String images;

	@Column(name = "PRICE", nullable = false)
	private Float price;

	@Column(name = "DOWN_PAYMENT", nullable = false)
	private Float downPayment;

	@Column(name = "AREA", nullable = false)
	private String area;

	@Column(name = "UTILITY", nullable = false)
	private String utility;

	@Column(name = "AVAILABLE", nullable = false)
	private Boolean available;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID", nullable = false)
	private Department department;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerRoom> customerRoom;

}
