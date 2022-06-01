/**
 * 
 */
package com.department.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.AssignedUserRoleDTO;
import com.department.model.entity.AssignedUserRole;

/**
 * @author bao.pham
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssignedUserRoleMapper {
  
  @Mapping(target = "user", ignore = true)
  AssignedUserRoleDTO toDto(AssignedUserRole entity);

  AssignedUserRole toEntity(AssignedUserRoleDTO dto);

  @InheritInverseConfiguration(name = "toDto")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "assignedUserRoleId", ignore = true)
  void patch(AssignedUserRoleDTO dto, @MappingTarget AssignedUserRole entity);
}