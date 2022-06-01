package com.department.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.common.HttpStatusEnum;
import com.department.exception.ServerException;
import com.department.mapper.DepartmentMapper;
import com.department.mapper.RoomMapper;
import com.department.model.dto.DepartmentDTO;
import com.department.model.dto.RoomDTO;
import com.department.model.entity.Department;
import com.department.repository.DepartmentRepository;
import com.department.service.DepartmentService;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	DepartmentMapper departmentMapper;

	@Autowired
	RoomMapper roomMapper;

	@Override
	public DepartmentDTO save(DepartmentDTO model) throws Exception {
		log.info("Saving new Department: {} ", model.getName());
		Department entity = departmentMapper.toEntity(model);
		return departmentMapper.toDto(departmentRepository.save(entity));
	}

	@Override
	public DepartmentDTO update(DepartmentDTO model, Long id) {
		log.debug("Update Department by id : {}", id);
		Department department = departmentRepository.findFirstById(id);
		if (department != null) {
			return departmentMapper.toDto(departmentRepository.save(department));
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public DepartmentDTO findById(Long id) throws Exception {
		log.debug("Find Department by id : {}", id);
		Department department = departmentRepository.findById(id).get();
		if (department != null) {
			return departmentMapper.toDto(department);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public List<DepartmentDTO> findAll() {
		log.debug("Find All Department: ");
		return departmentRepository.findAll().stream().map(departmentMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Long deleteById(Long id) {
		log.debug("Delete Department by id : {}", id);
		departmentRepository.deleteById(id);
		return id;
	}

	@Override
	public List<RoomDTO> getAllRoomById(long id) throws Exception {
		log.debug("Get All Room by department id : {}", id);
		Department department = departmentRepository.findFirstById(id);
		if (department != null) {
			return department.getRooms().stream().map(roomMapper::toDto).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public Department findByDepartmentId(long id) {
		log.debug("Find by department id : {}", id);
		return departmentRepository.findFirstById(id);
	}
}
