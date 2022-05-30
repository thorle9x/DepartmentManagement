/**
 * 
 */
package com.office.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.office.model.dto.UserDTO;
import com.office.model.search.UserSearchCriteria;

/**
 * @author bao.pham
 *
 */
public interface UserService extends IService<UserDTO> {
	UserDTO findByUserName(String username);

	Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable);
}
