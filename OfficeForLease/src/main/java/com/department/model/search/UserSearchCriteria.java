/**
 * 
 */
package com.department.model.search;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.pham
 *
 */

@Getter
@Setter
public class UserSearchCriteria extends AbstractSearchCriteria {

  String username;
  Long userId;
}
