package com.department.service.impl;

import java.util.Arrays;
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
import com.department.model.entity.Customer;
import com.department.model.entity.Department;
import com.department.model.entity.Room;
import com.department.repository.CustomerRepository;
import com.department.repository.RoomRepository;
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

	@Override
	public CustomerDTO save(CustomerDTO model) throws Exception {
		log.info("Saving new Customer: {} ", model.getName());
		Customer entity = customerMapper.toEntity(model);
		return customerMapper.toDto(customerRepository.save(entity));
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
	public List<CustomerDTO> findByRoomId(Long roomId) {
		Room room = roomRepository.findById(roomId).get();
		return customerRepository.findAllByRoomsIn(Arrays.asList(room)).stream().map(customerMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Set<CustomerDTO> findByDeparmentId(Long departmentId) {
		Department entity = departmentService.findByDepartmentId(departmentId);
		List<Room> listRoom = roomRepository.findByDepartmentAndAvailable(entity, true);
		return customerRepository.findAllByRoomsIn(listRoom).stream().map(customerMapper::toDto).collect(Collectors.toSet());
	}

}
