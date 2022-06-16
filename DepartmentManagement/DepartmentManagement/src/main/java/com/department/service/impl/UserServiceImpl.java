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

import com.department.exception.ResponseException;
import com.department.mapper.UserMapper;
import com.department.model.dto.UserDTO;
import com.department.model.entity.Role;
import com.department.model.entity.User;
import com.department.model.entity.UserRole;
import com.department.model.search.UserSearchCriteria;
import com.department.model.search.UserSpecification;
import com.department.repository.RoleRepository;
import com.department.repository.UserRepository;
import com.department.service.RoleService;
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
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

//	@Autowired
//	UserRoleService userRoleService;

	@Autowired
	RoleService roleService;

	@Override
	public UserDTO save(UserDTO model) {
		log.info("Saving new User: {} ", model.getUsername());
		User locaUser = userRepository.findFirstByUsername(model.getUsername());
		if (locaUser != null) {
			log.error("User with username {} already exist. Nothing will be done. ", model.getUsername());
			throw new ResponseException("USER_NAME_EXISTED", "Username existed!");
		}
		User entity = new User();
		userMapper.patch(model, entity);
		List<UserRole> userRoles = new ArrayList<>();
		List<String> requestUserRoles = model.getUserRoles();
		if (requestUserRoles != null && requestUserRoles.size() > 0) {
			requestUserRoles.forEach(role -> {
				Role roleEntity = roleRepository.findByRoleName(role);
				if (roleEntity == null) {
					throw new ResponseException("ROLE_NOT_FOUND", "Cannot find role!");
				}
				UserRole userRole = new UserRole();
				userRole.setCreatedBy(entity.getCreatedBy());
				userRole.setCreatedDate(entity.getCreatedDate());
				userRole.setIsActive(1l);
				userRole.setRole(roleEntity);
				userRole.setUser(entity);
				userRoles.add(userRole);
			});
		}
		entity.setUserRoles(userRoles);
		entity.setPassword(passwordEncoder.encode(model.getPassword()));
		UserDTO user = userMapper.toDto(userRepository.save(entity));
		return user;
	}

	public UserDTO update(UserDTO model, Long id) {
		log.debug("Update User by id : {}", id);
		User user = userRepository.findFirstByUserId(id);
		if (user != null) {
			userMapper.patch(model, user);
			return userMapper.toDto(userRepository.save(user));
		}
		throw new ResponseException("USER_NOT_FOUND", "Cannot find user!");
	}

	@Override
	public UserDTO findById(Long id) {
		log.debug("Find User by id : {}", id);
		User user = userRepository.findFirstByUserId(id);
		if (user != null) {
			return userMapper.toDto(user);
		}
		throw new ResponseException("USER_NOT_FOUND", "Cannot find user!");
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
		throw new ResponseException("USER_NOT_FOUND", "Cannot find user!");
	}

	@Override
	public Page<UserDTO> search(UserSearchCriteria criteria, Pageable pageable) {
		Specification<User> campaignSpecification = UserSpecification.search(criteria);
		Page<UserDTO> crmTransferClientList = userRepository.findAll(campaignSpecification, criteria.pageRequest()).map(userMapper::toDto);
		return crmTransferClientList;
	}

}