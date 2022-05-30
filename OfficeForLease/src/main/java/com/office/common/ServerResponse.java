/**
 * 
 */
package com.office.common;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author bao.pham
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ServerResponse implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 4359790903612064113L;

  private int status;
  @JsonIgnoreProperties(value = {"responseData", "hibernateLazyInitializer"})
  private Object data;
  private String message;

  public ServerResponse(HttpStatusEnum httpStatusEnum,  Object... params) {
    this.status = httpStatusEnum.getHttpStatus();
    this.message = httpStatusEnum.getMessageCode();
  }

  public ServerResponse setResult(Object data) {
    this.data = data;
    return this;
  }

}