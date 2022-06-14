package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.CustomerRoomDTO;
import com.department.model.dto.RoomDTO;
import com.department.model.entity.CustomerRoom;
import com.department.model.entity.Room;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {
	@Mapping(source = "department.id", target = "departmentId")
	@Mapping(target = "customerRoom", qualifiedByName = "customerRoomMapper")
	RoomDTO toDto(Room entity);

	Room toEntity(RoomDTO dto);

	@Mapping(target = "id", ignore = true)
	void patch(RoomDTO dto, @MappingTarget Room entity);

	@Named("customerRoomMapper")
	@Mapping(target = "room", ignore = true)
	CustomerRoomDTO toDto(CustomerRoom entity);
}
