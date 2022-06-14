package com.department.model.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 
 * @author bao.pham
 *
 */
@Data
public class AbstractSearchCriteria {

    Integer page = 0;
    Integer size = Integer.MAX_VALUE;
    Integer offset;
    String key;
    String sortField = "id";
    Sort.Direction sortOrder = Sort.DEFAULT_DIRECTION;
    String timeZone;
    @JsonIgnore
    public PageRequest pageRequest() {
        return PageRequest.of(this.page, this.size, Sort.by(sortOrder, sortField));
    }

}