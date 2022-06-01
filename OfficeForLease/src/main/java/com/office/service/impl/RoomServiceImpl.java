package com.office.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.common.HttpStatusEnum;
import com.office.exception.ServerException;
import com.office.mapper.DepartmentMapper;
import com.office.mapper.RoomMapper;
import com.office.model.dto.RoomDTO;
import com.office.model.entity.Department;
import com.office.model.entity.Room;
import com.office.repository.RoomRepository;
import com.office.service.DepartmentService;
import com.office.service.RoomService;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	RoomMapper roomMapper;

	@Autowired
	DepartmentMapper departmentMapper;

	@Override
	public RoomDTO save(RoomDTO model) throws Exception {
		log.info("Saving new Room: {} ", model.getName());
		try {
			Department department = departmentMapper.toEntity(departmentService.findById(model.getDepartmentId()));
			Room entity = roomMapper.toEntity(model);
			entity.setDepartment(department);
			return roomMapper.toDto(roomRepository.save(entity));
		} catch (Exception e) {
			throw new ServerException(HttpStatusEnum.UNEXPECTED, model);
		}
	}

	@Override
	public RoomDTO update(RoomDTO model, Long id) {
		log.debug("Update Room by id : {}", id);
		Room entity = roomRepository.findById(id).get();
		if (entity != null) {
			return roomMapper.toDto(roomRepository.save(entity));
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public RoomDTO findById(Long id) throws Exception {
		log.debug("Find Room by id : {}", id);
		Room entity = roomRepository.findById(id).get();
		if (entity != null) {
			return roomMapper.toDto(entity);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public List<RoomDTO> findAll() {
		log.debug("Find All Room: ");
		return roomRepository.findAll().stream().map(roomMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public Long deleteById(Long id) {
		log.debug("Delete Room by id : {}", id);
		roomRepository.deleteById(id);
		return id;
	}

	@Override
	public List<RoomDTO> findByDepartmentId(long id) {
		log.debug("Find Room by department id : {}", id);
		Department entity = departmentService.findByDepartmentId(id);
		return roomRepository.findByDepartment(entity).stream().map(roomMapper::toDto).collect(Collectors.toList());
	}

}
