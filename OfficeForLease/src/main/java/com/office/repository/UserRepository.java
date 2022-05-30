/**
 * 
 */
package com.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.office.model.entity.User;

/**
 * @author bao.pham
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  
  User findFirstByUsername(String username);
  
  User findFirstByUserId(Long userId);

}