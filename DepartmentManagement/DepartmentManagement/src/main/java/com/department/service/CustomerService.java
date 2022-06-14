package com.department.service;

import java.util.Set;

import com.department.model.dto.CustomerDTO;

public interface CustomerService extends IService<CustomerDTO> {
	Set<CustomerDTO> findByRoomId(Long roomId);
	
	Set<CustomerDTO> findByDeparmentId(Long departmentId);
}
