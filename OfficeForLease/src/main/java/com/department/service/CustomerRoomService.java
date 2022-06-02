package com.department.service;

import java.util.List;

import com.department.model.entity.CustomerRoom;
import com.department.model.entity.Room;

public interface CustomerRoomService extends IService<CustomerRoom> {
	List<CustomerRoom> findAllByCustomerId(Long customerId);
	
	List<CustomerRoom> findAllByRoomId(Long roomId);

	List<CustomerRoom> findAllByRoomInAndIsRented(List<Room> listRoom, Boolean isRented);
}
