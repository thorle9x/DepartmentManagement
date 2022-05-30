/**
 * 
 */
package com.office.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.office.common.HttpStatusEnum;
import com.office.exception.ServerException;
import com.office.mapper.AssignedUserRoleMapper;
import com.office.model.dto.AssignedUserRoleDTO;
import com.office.model.entity.AssignedUserRole;
import com.office.repository.AssignedUserRoleRepository;
import com.office.service.AssignedUserRoleService;

import lombok.extern.log4j.Log4j2;

/**
 * @author bao.pham
 *
 */
@Service
@Transactional
@Log4j2
public class AssignedUserRoleServiceImpl implements AssignedUserRoleService {

	@Autowired
	AssignedUserRoleRepository assignedUserRoleRepository;

	@Autowired
	AssignedUserRoleMapper assignedUserRoleMapper;

	@Override
	public AssignedUserRoleDTO save(AssignedUserRoleDTO model) throws Exception {
		log.info("Saving new Assigned User Role: {} ");
		AssignedUserRole assignedUserRole = assignedUserRoleMapper.toEntity(model);
		return assignedUserRoleMapper.toDto(assignedUserRoleRepository.save(assignedUserRole));
	}

	@Override
	public AssignedUserRoleDTO update(AssignedUserRoleDTO model, Long id) {
		log.info("Update Assigned User Role: {} ");
		AssignedUserRole assignedUserRole = assignedUserRoleRepository.findFirstByAssignedUserRoleId(id);
		if (assignedUserRole != null) {
			assignedUserRoleMapper.patch(model, assignedUserRole);
			return assignedUserRoleMapper.toDto(assignedUserRoleRepository.save(assignedUserRole));
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public AssignedUserRoleDTO findById(Long id) throws Exception {
		AssignedUserRole assignedUserRole = assignedUserRoleRepository.findFirstByAssignedUserRoleId(id);
		return assignedUserRoleMapper.toDto(assignedUserRole);
	}

	@Override
	public Long deleteById(Long id) {
		assignedUserRoleRepository.deleteById(id);
		return id;
	}

	@Override
	public List<AssignedUserRoleDTO> findAll() {
		return assignedUserRoleRepository.findAll().stream().map(assignedUserRoleMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<AssignedUserRoleDTO> saveAll(List<AssignedUserRoleDTO> entities) {
		if (ObjectUtils.isEmpty(entities)) {
			return entities;
		}
		List<AssignedUserRole> listAssignedUserRole = entities.stream().map(assignedUserRoleMapper::toEntity)
				.collect(Collectors.toList());
		listAssignedUserRole = assignedUserRoleRepository.saveAll(listAssignedUserRole);
		return listAssignedUserRole.stream().map(assignedUserRoleMapper::toDto).collect(Collectors.toList());
	}
}