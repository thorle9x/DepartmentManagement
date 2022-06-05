package com.department.service;

import com.department.model.dto.DepartmentDTO;
import com.department.model.entity.Department;

public interface DepartmentService extends IService<DepartmentDTO>{
	Department findByDepartmentId(long id);
}
