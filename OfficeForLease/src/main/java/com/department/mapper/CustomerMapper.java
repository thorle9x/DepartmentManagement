package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.CustomerDTO;
import com.department.model.entity.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
	CustomerDTO toDto(Customer entity);

	Customer toEntity(CustomerDTO dto);
}
