/**
 * 
 */
package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.RoleDTO;
import com.department.model.entity.Role;

/**
 * @author bao.pham
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

  RoleDTO toDto(Role entity);

  Role toEntity(RoleDTO dto);
}
