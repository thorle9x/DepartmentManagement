/**
 * 
 */
package com.office.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.office.model.entity.AssignedUserRole;
/**
 * @author bao.pham
 *
 */
@Repository
public interface AssignedUserRoleRepository
    extends JpaRepository<AssignedUserRole, Long>, JpaSpecificationExecutor<AssignedUserRole> {
  
  AssignedUserRole findFirstByAssignedUserRoleId(Long assignedUserRoleId);

}