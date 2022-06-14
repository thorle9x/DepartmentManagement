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
import com.department.mapper.RoleMapper;
import com.department.model.dto.RoleDTO;
import com.department.model.entity.Role;
import com.department.repository.RoleRepository;
import com.department.service.UserRoleService;

import lombok.extern.log4j.Log4j2;

/**
 * @author bao.pham
 *
 */
@Service
@Transactional
@Log4j2
public class RoleServiceImpl implements UserRoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleMapper roleMapper;

	@Override
	public RoleDTO save(RoleDTO model) throws Exception {
		log.info("Saving new User Role: {} ", model.getRoleName());
		Role role = roleMapper.toEntity(model);
		return roleMapper.toDto(roleRepository.save(role));
	}

	@Override
	public RoleDTO update(RoleDTO model, Long id) {
		log.debug("Update User Role by id : {}", id);
		Role role = roleRepository.findFirstByRoleId(id);
		if (role != null) {
			return roleMapper.toDto(roleRepository.save(role));
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public RoleDTO findById(Long id) throws Exception {
		log.debug("Find User Role by id : {}", id);
		Role role = roleRepository.findFirstByRoleId(id);
		if (role != null) {
			return roleMapper.toDto(role);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public List<RoleDTO> findAll() {
		log.debug("Find All User Role: ");
		return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Long deleteById(Long id) {
		log.debug("Delete User Role by id : {}", id);
		roleRepository.deleteById(id);
		return id;
	}

}