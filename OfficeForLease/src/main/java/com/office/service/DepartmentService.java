package com.office.service;

import java.util.List;

import com.office.model.dto.DepartmentDTO;
import com.office.model.dto.RoomDTO;
import com.office.model.entity.Department;

public interface DepartmentService extends IService<DepartmentDTO>{
	List<RoomDTO> getAllRoomById(long id) throws Exception;
	
	Department findByDepartmentId(long id);
}
