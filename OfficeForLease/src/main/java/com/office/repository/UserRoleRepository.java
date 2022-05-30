/**
 * 
 */
package com.office.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.office.model.entity.UserRole;

/**
 * @author bao.pham
 *
 */
@Repository
public interface UserRoleRepository
    extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
  
  UserRole findFirstByUserRoleId(Long userRoleId);

}