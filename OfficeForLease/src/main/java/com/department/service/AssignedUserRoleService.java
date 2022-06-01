/**
 * 
 */
package com.department.service;

import java.util.List;

import com.department.model.dto.AssignedUserRoleDTO;

/**
 * @author bao.pham
 *
 */
public interface AssignedUserRoleService extends IService<AssignedUserRoleDTO> {

	List<AssignedUserRoleDTO> saveAll(List<AssignedUserRoleDTO> entities);

}
