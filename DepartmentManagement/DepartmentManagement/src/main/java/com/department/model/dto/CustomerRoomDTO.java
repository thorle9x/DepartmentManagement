package com.department.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRoomDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private long id;

	private Boolean isRented;

	private RoomDTO room;

	private CustomerDTO customer;

}
