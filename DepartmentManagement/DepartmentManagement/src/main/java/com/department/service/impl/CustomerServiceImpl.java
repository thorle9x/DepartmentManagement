package com.department.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.common.HttpStatusEnum;
import com.department.exception.ServerException;
import com.department.mapper.CustomerMapper;
import com.department.model.dto.CustomerDTO;
import com.department.model.dto.CustomerRoomDTO;
import com.department.model.dto.RoomDTO;
import com.department.model.entity.Customer;
import com.department.model.entity.CustomerRoom;
import com.department.model.entity.Department;
import com.department.model.entity.Room;
import com.department.repository.CustomerRepository;
import com.department.repository.RoomRepository;
import com.department.service.CustomerRoomService;
import com.department.service.CustomerService;
import com.department.service.DepartmentService;
import com.department.service.RoomService;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomService roomService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	CustomerRoomService customerRoomService;

	@Override
	public CustomerDTO save(CustomerDTO model) throws Exception {
		log.info("Saving new Customer: {} ", model.getName());
		Customer customerEntity = customerMapper.toEntity(model);
		List<CustomerRoom> customerRoomList = new ArrayList<>();

		List<CustomerRoomDTO> customerRoomListDto = model.getCustomerRoom();
		customerRoomListDto.forEach(csDto -> {
			CustomerRoom customerRoom = new CustomerRoom();
			RoomDTO room = csDto.getRoom();
			if (room != null && room.getId() > 0) {
				Room roomEntity = roomRepository.findById(room.getId()).get();
				if (roomEntity != null) {
					customerRoom.setRoom(roomEntity);
					customerRoom.setCustomer(customerEntity);
					customerRoom.setIsRented(csDto.getIsRented());
					customerRoom.setCreatedBy(model.getCreatedBy());
					customerRoom.setCreatedDate(model.getCreatedDate());

					customerRoomList.add(customerRoom);
				}
			}
		});

		customerEntity.setCustomerRoom(customerRoomList);
		return customerMapper.toDto(customerRepository.save(customerEntity));
	}

	@Override
	public CustomerDTO update(CustomerDTO model, Long id) {
		log.debug("Update Customer by id : {}", id);
		Customer customer = customerRepository.findFirstById(id);
		if (customer != null) {
			return customerMapper.toDto(customerRepository.save(customer));
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public CustomerDTO findById(Long id) throws Exception {
		log.debug("Find Customer by id : {}", id);
		Customer customer = customerRepository.findById(id).get();
		if (customer != null) {
			return customerMapper.toDto(customer);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public List<CustomerDTO> findAll() {
		log.debug("Find All Customer: ");
		return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Long deleteById(Long id) {
		log.debug("Delete Customer by id : {}", id);
		customerRepository.deleteById(id);
		return id;
	}

	@Override
	public Set<CustomerDTO> findByRoomId(Long roomId) {
		Room roomEntity = roomRepository.findById(roomId).get();
		List<Room> listRoom = new ArrayList<Room>();
		listRoom.add(roomEntity);
		List<CustomerRoom> customerRoomList = customerRoomService.findAllByRoomInAndIsRented(listRoom, true);
		return customerRoomList.stream().map(cs -> customerMapper.toDto(cs.getCustomer())).collect(Collectors.toSet());
	}

	@Override
	public Set<CustomerDTO> findByDeparmentId(Long departmentId) {
		Department entity = departmentService.findByDepartmentId(departmentId);
		List<Room> listRoom = roomRepository.findByDepartmentAndAvailable(entity, true);
		List<CustomerRoom> customerRoomList = customerRoomService.findAllByRoomInAndIsRented(listRoom, true);
		return customerRoomList.stream().map(cs -> customerMapper.toDto(cs.getCustomer())).collect(Collectors.toSet());
	}

}
