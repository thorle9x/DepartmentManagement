/**
 * 
 */
package com.department.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.department.model.dto.UserDTO;
import com.department.model.search.UserSearchCriteria;

public interface UserService extends IService<UserDTO> {
	UserDTO findByUserName(String username);

	Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable);
}
