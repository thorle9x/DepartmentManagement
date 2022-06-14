package com.department.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.CustomerRoomDTO;
import com.department.model.entity.CustomerRoom;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerRoomMapper {
	@Mapping(target = "customer", ignore = true)
	CustomerRoomDTO toDto(CustomerRoom entity);

	CustomerRoom toEntity(CustomerRoomDTO dto);

	@InheritInverseConfiguration(name = "toDto")
	@Mapping(target = "id", ignore = true)
	void patch(CustomerRoomDTO dto, @MappingTarget CustomerRoom entity);
}
