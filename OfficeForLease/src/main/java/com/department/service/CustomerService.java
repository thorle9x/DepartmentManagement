package com.department.service;

import java.util.List;

import com.department.model.dto.CustomerDTO;

public interface CustomerService extends IService<CustomerDTO> {
	List<CustomerDTO> findByRoomId(Long roomId);
}
