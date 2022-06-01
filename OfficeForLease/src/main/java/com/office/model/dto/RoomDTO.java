package com.office.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 761432039990380309L;

	private long id;

	private String code;

	private String name;

	private String address;

	private String images;

	private Float price;

	private Float downPayment;

	private String area;

	private String utility;

	private Boolean available;

	private long departmentId;

}
