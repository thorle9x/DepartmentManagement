package com.department.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.common.HttpStatusEnum;
import com.department.exception.ServerException;
import com.department.model.entity.CustomerRoom;
import com.department.model.entity.Room;
import com.department.repository.CustomerRoomRepository;
import com.department.service.CustomerRoomService;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class CustomerRoomImpl implements CustomerRoomService {

	@Autowired
	CustomerRoomRepository customerRoomRepo;

	@Override
	public CustomerRoom save(CustomerRoom model) throws Exception {
		log.info("Saving new Customer: {} and Room: {}", model.getCustomer().getName(), model.getRoom().getName());
		return customerRoomRepo.save(model);
	}

	@Override
	public CustomerRoom update(CustomerRoom model, Long id) {
		log.debug("Update Customer: {} and Room: {}", model.getCustomer().getName(), model.getRoom().getName());
		CustomerRoom customerRoom = customerRoomRepo.findById(id).get();
		if (customerRoom != null) {
			return customerRoomRepo.save(customerRoom);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public CustomerRoom findById(Long id) throws Exception {
		log.debug("Find Customer Room by id : {}", id);
		CustomerRoom customerRoom = customerRoomRepo.findById(id).get();
		if (customerRoom != null) {
			return customerRoom;
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public List<CustomerRoom> findAll() {
		log.debug("Find All Customer Room: ");
		return customerRoomRepo.findAll();
	}

	@Override
	public Long deleteById(Long id) {
		log.debug("Delete Customer Room by id : {}", id);
		customerRoomRepo.deleteById(id);
		return id;
	}

	@Override
	public List<CustomerRoom> findAllByCustomerId(Long customerId) {
		List<CustomerRoom> customerRoomList = customerRoomRepo.findAllByCustomerId(customerId);
		return customerRoomList;
	}

	@Override
	public List<CustomerRoom> findAllByRoomInAndIsRented(List<Room> listRoom, Boolean isRented) {
		List<CustomerRoom> customerRoomList = customerRoomRepo.findAllByRoomInAndIsRented(listRoom, isRented);
		return customerRoomList;
	}

	@Override
	public List<CustomerRoom> findAllByRoomId(Long roomId) {
		return customerRoomRepo.findAllByRoomId(roomId);
	}

}
