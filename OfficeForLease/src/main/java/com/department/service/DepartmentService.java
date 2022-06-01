package com.department.service;

import java.util.List;

import com.department.model.dto.DepartmentDTO;
import com.department.model.dto.RoomDTO;
import com.department.model.entity.Department;

public interface DepartmentService extends IService<DepartmentDTO>{
	List<RoomDTO> getAllRoomById(long id) throws Exception;
	
	Department findByDepartmentId(long id);
}
