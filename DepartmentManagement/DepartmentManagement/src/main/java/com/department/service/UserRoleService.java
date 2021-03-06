/**
 * 
 */
package com.department.service;

import java.util.List;

import com.department.model.dto.UserRoleDTO;

/**
 * @author bao.pham
 *
 */
public interface UserRoleService extends IService<UserRoleDTO> {

	List<UserRoleDTO> saveAll(List<UserRoleDTO> entities);

}
