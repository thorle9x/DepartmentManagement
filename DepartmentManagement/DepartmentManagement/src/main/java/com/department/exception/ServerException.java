/**
 * 
 */
package com.department.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.department.common.HttpStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2667999585175015919L;
	private HttpStatusEnum httpStatus;
	private int status;
	private String message;
	private List<Object> parameters = new ArrayList<Object>();

	public ServerException(HttpStatusEnum httpStatus, Object... params) {
		this(null, httpStatus, params);
	}

	public ServerException(Throwable cause, HttpStatusEnum httpStatus, Object... params) {
		super(httpStatus.getMessageCode(), cause);
		this.setHttpStatus(httpStatus);
		this.setStatus(status);
		this.setMessage(httpStatus.getMessageCode());
		for (Object param : params) {
			this.parameters.add(param);
		}
	}

	public List<Object> getParameters() {
		return Collections.unmodifiableList(parameters);
	}

	public void addParameters(Object param) {
		this.parameters.add(param);
	}
}