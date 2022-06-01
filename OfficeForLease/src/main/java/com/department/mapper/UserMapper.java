/**
 * 
 */
package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.AssignedUserRoleDTO;
import com.department.model.dto.UserDTO;
import com.department.model.entity.AssignedUserRole;
import com.department.model.entity.User;

/**
 * 
 * @author bao.pham
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  @Mapping(target = "assignedUserRoles", qualifiedByName = "assignedUserRoleMapper")
  UserDTO toDto(User entity);

  User toEntity(UserDTO dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  void patch(UserDTO dto, @MappingTarget User entity);

  @Named("assignedUserRoleMapper")
  @Mapping(target = "user", ignore = true)
  AssignedUserRoleDTO toDto(AssignedUserRole entity);
}
