/**
 * 
 */
package com.office.service.impl;
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

import com.office.common.CommonUtil;
import com.office.common.HttpStatusEnum;
import com.office.exception.ServerException;
import com.office.mapper.UserMapper;
import com.office.model.dto.UserDTO;
import com.office.model.dto.UserRoleDTO;
import com.office.model.entity.User;
import com.office.model.search.UserSearchCriteria;
import com.office.model.search.UserSpecification;
import com.office.repository.UserRepository;
import com.office.service.AssignedUserRoleService;
import com.office.service.UserRoleService;
import com.office.service.UserService;

import lombok.extern.log4j.Log4j2;
/**
 * @author bao.pham
 *
 */
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
    entity.setAssignedUserRoles(null);
    UserDTO user = userMapper.toDto(userRepository.saveAndFlush(entity));
    // handle create assign user role
    if(!ObjectUtils.isEmpty(model.getAssignedUserRoles())) {
      model.getAssignedUserRoles().stream().forEach(assignedUserRole -> {
        assignedUserRole.setUser(user);
        assignedUserRole.setAssignedUserRoleId(CommonUtil.generateID());
        try {
          UserRoleDTO role = userRoleService.findById(assignedUserRole.getUserRole().getUserRoleId());
          assignedUserRole.setUserRole(role);
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      });
      
      assignedUserRoleService.saveAll(model.getAssignedUserRoles());
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
      throw new ServerException(HttpStatusEnum.NO_RECORD_FOUND, username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getAssignedUserRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getUserRole().getRoleName()));
    });

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), authorities);
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
    Page<UserDTO> crmTransferClientList = userRepository
        .findAll(campaignSpecification, criteria.pageRequest()).map(userMapper::toDto);
    return crmTransferClientList;
  }

}