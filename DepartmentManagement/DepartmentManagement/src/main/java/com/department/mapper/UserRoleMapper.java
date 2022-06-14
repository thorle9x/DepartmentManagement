/**
 * 
 */
package com.department.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.UserRoleDTO;
import com.department.model.entity.UserRole;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {
  
  @Mapping(target = "user", ignore = true)
  UserRoleDTO toDto(UserRole entity);

  UserRole toEntity(UserRoleDTO dto);

  @InheritInverseConfiguration(name = "toDto")
  @Mapping(target = "id", ignore = true)
  void patch(UserRoleDTO dto, @MappingTarget UserRole entity);
}