package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.CustomerDTO;
import com.department.model.dto.CustomerRoomDTO;
import com.department.model.entity.Customer;
import com.department.model.entity.CustomerRoom;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
	
	@Mapping(target = "customerRoom", qualifiedByName = "customerRoomMapper")
	CustomerDTO toDto(Customer entity);

	Customer toEntity(CustomerDTO dto);
	
	@Mapping(target = "id", ignore = true)
	void patch(CustomerDTO dto, @MappingTarget Customer entity);

	@Named("customerRoomMapper")
	@Mapping(target = "customer", ignore = true)
	CustomerRoomDTO toDto(CustomerRoom entity);
}
