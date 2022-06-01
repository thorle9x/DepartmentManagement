package com.office.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.office.model.dto.DepartmentDTO;
import com.office.model.entity.Department;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
	
	DepartmentDTO toDto(Department entity);

	Department toEntity(DepartmentDTO dto);
}
