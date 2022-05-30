package com.office.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class AbstractDTO implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 7131627638763051851L;

  private Timestamp updatedDate;

  private String updatedBy;


  private Timestamp createdDate;

  private String createdBy;
}
