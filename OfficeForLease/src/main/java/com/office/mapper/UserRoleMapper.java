/**
 * 
 */
package com.office.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.office.model.dto.UserRoleDTO;
import com.office.model.entity.UserRole;

/**
 * @author bao.pham
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {

  UserRoleDTO toDto(UserRole entity);

  UserRole toEntity(UserRoleDTO dto);
}
