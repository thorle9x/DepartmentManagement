package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.UserDTO;
import com.department.model.entity.User;
import com.department.model.entity.UserRole;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	@Mapping(target = "userRoles", source = "userRoles", qualifiedByName = "UserRoleMapper")
	UserDTO toDto(User entity);

	@Mapping(target = "userRoles", ignore = true)
	User toEntity(UserDTO dto);

	@Mapping(target = "userId", ignore = true)
	@Mapping(target = "userRoles", ignore = true)
	void patch(UserDTO dto, @MappingTarget User entity);

	@Named("UserRoleMapper")
	default String map(UserRole entity) {
		if (entity == null || entity.getRole() == null) {
			return "";
		}
		return entity.getRole().getRoleName();
	}

}
