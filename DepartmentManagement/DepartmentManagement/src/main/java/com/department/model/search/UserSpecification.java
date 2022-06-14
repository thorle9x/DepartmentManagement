/**
 * 
 */
package com.department.model.search;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.department.model.entity.User;

/**
 * @author bao.pham
 *
 */
public class UserSpecification {

  public static Specification<User> search(UserSearchCriteria filter) {
    return (Specification<User>) (root, query, cb) -> {
      Predicate p1 = cb.and(); // true
      Predicate p2 = cb.and(); // true
      List<Predicate> sTp = new ArrayList<>();

      if (filter.getUserId() != null && filter.getUserId() > 0) {
        p1 = cb.equal(root.get("userId"), filter.getUserId());
        sTp.add(p1);
      }

      if (isInputValid(filter.getUsername())) {
        p2 = cb.like(root.get("username"), normalize(filter.getUsername()));
        sTp.add(p2);
      }

      return cb.and(sTp.toArray(new Predicate[sTp.size()]));
    };
  }

  private static boolean isInputValid(String input) {
    return !(input == null) && !input.isEmpty();
  }

  private static String normalize(String text) {
    if (ObjectUtils.isEmpty(text))
      return "%";
    return "%" + text + "%";
  }

}
