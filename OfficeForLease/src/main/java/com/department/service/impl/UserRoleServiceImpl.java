/**
 * 
 */
package com.department.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.common.HttpStatusEnum;
import com.department.exception.ServerException;
import com.department.mapper.UserRoleMapper;
import com.department.model.dto.UserRoleDTO;
import com.department.model.entity.UserRole;
import com.department.repository.UserRoleRepository;
import com.department.service.UserRoleService;

import lombok.extern.log4j.Log4j2;

/**
 * @author bao.pham
 *
 */
@Service
@Transactional
@Log4j2
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public UserRoleDTO save(UserRoleDTO model) throws Exception {
		log.info("Saving new User Role: {} ", model.getRoleName());
		UserRole role = userRoleMapper.toEntity(model);
		return userRoleMapper.toDto(userRoleRepository.save(role));
	}

	@Override
	public UserRoleDTO update(UserRoleDTO model, Long id) {
		log.debug("Update User Role by id : {}", id);
		UserRole role = userRoleRepository.findFirstByUserRoleId(id);
		if (role != null) {
			return userRoleMapper.toDto(userRoleRepository.save(role));
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public UserRoleDTO findById(Long id) throws Exception {
		log.debug("Find User Role by id : {}", id);
		UserRole role = userRoleRepository.findFirstByUserRoleId(id);
		if (role != null) {
			return userRoleMapper.toDto(role);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public List<UserRoleDTO> findAll() {
		log.debug("Find All User Role: ");
		return userRoleRepository.findAll().stream().map(userRoleMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Long deleteById(Long id) {
		log.debug("Delete User Role by id : {}", id);
		userRoleRepository.deleteById(id);
		return id;
	}

}