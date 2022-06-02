package com.department.model.entity;

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
@Table(name = "CUSTOMER_ROOM")
@Getter
@Setter
public class CustomerRoom extends AbstractEntity {

	private static final long serialVersionUID = 7821460008580563302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "IS_RENTED", nullable = false)
	private Boolean isRented;
	
	@ManyToOne
	@JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")
	@JsonIgnore
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
	@JsonIgnore
	private Customer customer;

}
