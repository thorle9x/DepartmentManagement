/**
 * 
 */
package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.UserRoleDTO;
import com.department.model.dto.UserDTO;
import com.department.model.entity.UserRole;
import com.department.model.entity.User;

/**
 * 
 * @author bao.pham
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	@Mapping(target = "userRoles", source = "userRoles", qualifiedByName = "UserRoleMapper")
	UserDTO toDto(User entity);

	User toEntity(UserDTO dto);

	@Mapping(target = "userId", ignore = true)
	void patch(UserDTO dto, @MappingTarget User entity);

	@Named("UserRoleMapper")
	@Mapping(target = "user", ignore = true)
	UserRoleDTO toDto(UserRole entity);
}
