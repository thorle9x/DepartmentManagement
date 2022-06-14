/**
 * 
 */
package com.department.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.department.common.CommonUtil;
import com.department.common.HttpStatusEnum;
import com.department.exception.ServerException;
import com.department.mapper.UserMapper;
import com.department.model.dto.RoleDTO;
import com.department.model.dto.UserDTO;
import com.department.model.entity.User;
import com.department.model.search.UserSearchCriteria;
import com.department.model.search.UserSpecification;
import com.department.repository.UserRepository;
import com.department.service.AssignedUserRoleService;
import com.department.service.UserRoleService;
import com.department.service.UserService;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AssignedUserRoleService assignedUserRoleService;

	@Autowired
	UserRoleService userRoleService;

	@Override
	public UserDTO save(UserDTO model) throws Exception {
		log.info("Saving new User: {} ", model.getUsername());
		User entity = new User();
		userMapper.patch(model, entity);
		// set userId
		entity.setUserId(CommonUtil.generateID());
		// encode password
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		entity.setUserRoles(null);
		UserDTO user = userMapper.toDto(userRepository.saveAndFlush(entity));
		// handle create assign user role
		if (!ObjectUtils.isEmpty(model.getUserRoles())) {
			model.getUserRoles().stream().forEach(assignedUserRole -> {
				assignedUserRole.setUser(user);
				assignedUserRole.setId(CommonUtil.generateID());
				try {
					RoleDTO role = userRoleService.findById(assignedUserRole.getRole().getRoleId());
					assignedUserRole.setRole(role);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			assignedUserRoleService.saveAll(model.getUserRoles());
		}
		return user;
	}

	@Override
	public UserDTO update(UserDTO model, Long id) {
		log.debug("Update User by id : {}", id);
		User user = userRepository.findFirstByUserId(id);
		if (user != null) {
			userMapper.patch(model, user);
			return userMapper.toDto(userRepository.save(user));
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public UserDTO findById(Long id) throws Exception {
		log.debug("Find User by id : {}", id);
		User user = userRepository.findFirstByUserId(id);
		if (user != null) {
			return userMapper.toDto(user);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, id);
	}

	@Override
	public Long deleteById(Long id) {
		userRepository.deleteById(id);
		return id;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findFirstByUsername(username);
		if (user == null) {
			log.error("No User by username : {}", username);
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getUserRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRole().getRoleName()));
		});

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public UserDTO findByUserName(String username) {
		User user = userRepository.findFirstByUsername(username);
		if (user != null) {
			return userMapper.toDto(user);
		}
		throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, username);
	}

	@Override
	public Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable) {
		Specification<User> campaignSpecification = UserSpecification.search(criteria);
		Page<UserDTO> crmTransferClientList = userRepository.findAll(campaignSpecification, criteria.pageRequest()).map(userMapper::toDto);
		return crmTransferClientList;
	}

}