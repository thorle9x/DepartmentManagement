package com.office.service;

import java.util.List;

import com.office.model.dto.RoomDTO;

public interface RoomService extends IService<RoomDTO> {
	List<RoomDTO> findByDepartmentId(long id);
}
