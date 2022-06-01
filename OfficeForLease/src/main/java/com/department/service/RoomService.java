package com.department.service;

import java.util.List;

import com.department.model.dto.RoomDTO;

public interface RoomService extends IService<RoomDTO> {
	List<RoomDTO> findByDepartmentId(long id);
}
