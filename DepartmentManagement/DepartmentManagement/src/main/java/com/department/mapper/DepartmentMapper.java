package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.DepartmentDTO;
import com.department.model.entity.Department;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
	
	DepartmentDTO toDto(Department entity);

	Department toEntity(DepartmentDTO dto);
}
