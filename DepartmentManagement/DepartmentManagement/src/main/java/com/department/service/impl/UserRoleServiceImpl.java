package com.department.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.department.exception.ResponseException;
import com.department.mapper.UserRoleMapper;
import com.department.model.dto.UserRoleDTO;
import com.department.model.entity.UserRole;
import com.department.repository.UserRoleRepository;
import com.department.service.UserRoleService;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public UserRoleDTO save(UserRoleDTO model) {
		log.info("Saving new Assigned User Role: {} ");
		UserRole userRole = userRoleMapper.toEntity(model);
		return userRoleMapper.toDto(userRoleRepository.save(userRole));
	}

	@Override
	public UserRoleDTO update(UserRoleDTO model, Long id) {
		log.info("Update Assigned User Role: {} ");
		UserRole userRole = userRoleRepository.findFirstById(id);
		if (userRole != null) {
			userRoleMapper.patch(model, userRole);
			return userRoleMapper.toDto(userRoleRepository.save(userRole));
		}
		throw new ResponseException("USER_ROLE_NOT_FOUND", "Cannot find user role!");
	}

	@Override
	public UserRoleDTO findById(Long id) {
		UserRole userRole = userRoleRepository.findFirstById(id);
		return userRoleMapper.toDto(userRole);
	}

	@Override
	public Long deleteById(Long id) {
		userRoleRepository.deleteById(id);
		return id;
	}

	@Override
	public List<UserRoleDTO> findAll() {
		return userRoleRepository.findAll().stream().map(userRoleMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<UserRoleDTO> saveAll(List<UserRoleDTO> entities) {
		if (ObjectUtils.isEmpty(entities)) {
			return entities;
		}
		List<UserRole> listAssignedUserRole = entities.stream().map(userRoleMapper::toEntity).collect(Collectors.toList());
		listAssignedUserRole = userRoleRepository.saveAll(listAssignedUserRole);
		return listAssignedUserRole.stream().map(userRoleMapper::toDto).collect(Collectors.toList());
	}
}