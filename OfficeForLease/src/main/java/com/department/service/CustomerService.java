package com.department.service;

import java.util.List;
import java.util.Set;

import com.department.model.dto.CustomerDTO;

public interface CustomerService extends IService<CustomerDTO> {
	List<CustomerDTO> findByRoomId(Long roomId);
	
	Set<CustomerDTO> findByDeparmentId(Long departmentId);
}
