/**
 * 
 */
package com.department.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.department.model.dto.UserRoleDTO;
import com.department.model.entity.UserRole;

/**
 * @author bao.pham
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {

  UserRoleDTO toDto(UserRole entity);

  UserRole toEntity(UserRoleDTO dto);
}
