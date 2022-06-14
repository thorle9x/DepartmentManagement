package com.department.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

public class ExceptionResponse {

	public Map<String, Object> getErrorAttributes(WebRequest webRequest) {
		Map<String, Object> errorAttributes = new LinkedHashMap<>();
		errorAttributes.put("timestamp", new Date());
		addStatus(errorAttributes, webRequest);
		addErrorMessage(errorAttributes, webRequest);
		addPath(errorAttributes, webRequest);
		return errorAttributes;
	}

	public Map<String, Object> getInternalErrorAttributes(WebRequest webRequest) {
		Map<String, Object> errorAttributes = new LinkedHashMap<>();
		errorAttributes.put("timestamp", new Date());
		addStatus(errorAttributes, webRequest);
		addErrorMessage(errorAttributes, webRequest);
		addPath(errorAttributes, webRequest);
		return errorAttributes;
	}

	private void addPath(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
		if (requestAttributes instanceof ServletWebRequest) {
			ServletWebRequest serveletRequest = (ServletWebRequest) requestAttributes;
			String contextPath = serveletRequest.getRequest().getRequestURI().toString();
			errorAttributes.put("path", contextPath);
		} else {
			String path = getAttribute(requestAttributes, RequestDispatcher.ERROR_REQUEST_URI);
			if (path != null) {
				errorAttributes.put("path", path);
			}
		}
	}

	private void addErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest) {
		addExceptionErrorMessage(errorAttributes, webRequest);
	}

	private void addExceptionErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest) {
		Object message = getAttribute(webRequest, RequestDispatcher.ERROR_MESSAGE);
		if (message == null) {
			message = "No message available";
		}
		errorAttributes.put("message", message);
	}

	private void addStatus(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
		Integer status = getAttribute(requestAttributes, RequestDispatcher.ERROR_STATUS_CODE);
		if (status == null) {
			errorAttributes.put("status", 999);
			errorAttributes.put("error", "None");
			return;
		}
		errorAttributes.put("status", status);
		try {
			String errorCode = getAttribute(requestAttributes, "ERROR_CODE");
			if (errorCode == null || errorCode.isEmpty()) {
				errorCode = HttpStatus.valueOf(status).getReasonPhrase();
			}
			errorAttributes.put("error", errorCode);
		} catch (Exception ex) {
			errorAttributes.put("error", "Http Status " + status);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
		return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
	}

}
