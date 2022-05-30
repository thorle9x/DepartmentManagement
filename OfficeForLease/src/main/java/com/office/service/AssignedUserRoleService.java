/**
 * 
 */
package com.office.service;

import java.util.List;

import com.office.model.dto.AssignedUserRoleDTO;

/**
 * @author bao.pham
 *
 */
public interface AssignedUserRoleService extends IService<AssignedUserRoleDTO> {

	List<AssignedUserRoleDTO> saveAll(List<AssignedUserRoleDTO> entities);

}
