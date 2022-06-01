package com.office.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.office.model.dto.RoomDTO;
import com.office.model.entity.Room;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {
	@Mapping(source = "department.id", target = "departmentId")
	RoomDTO toDto(Room entity);

	Room toEntity(RoomDTO dto);
}
