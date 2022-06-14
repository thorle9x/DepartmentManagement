package com.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.department.model.entity.CustomerRoom;
import com.department.model.entity.Room;

@Repository
public interface CustomerRoomRepository extends JpaRepository<CustomerRoom, Long>, JpaSpecificationExecutor<CustomerRoom> {

	List<CustomerRoom> findAllByRoomId(Long roomId);

	List<CustomerRoom> findAllByCustomerId(Long customerId);

	List<CustomerRoom> findAllByRoomInAndIsRented(List<Room> listRoom, Boolean isRented);

}
