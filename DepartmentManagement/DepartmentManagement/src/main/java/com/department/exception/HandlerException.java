package com.department.exception;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			builder.append(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			builder.append(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, builder.toString(), RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, error, RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Required request body is missing";

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, error, RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, error, RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, error, RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.METHOD_NOT_ALLOWED.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, builder.toString(), RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, builder.toString(), RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		String error = "Unknow Eception";

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, error, RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Invalid API URL";

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, error, RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		String error = "Permission Denied!";

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.FORBIDDEN.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, error , RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ResponseException.class})
	public ResponseEntity<Object> handleValidationInternalException(ResponseException ex, WebRequest request) {
		String error = "Bad request";
		if (ex != null) {
			error = ex.getMessage();
		}

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute("ERROR_CODE", error , RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, ex.getCause().getMessage(), RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
		String error = "Authenication failed";
		if (ex != null) {
			error = ex.getMessage();
		}

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_GATEWAY.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute("ERROR_CODE", error , RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, ex.getCause().getMessage(), RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		String error = "Bad request";
		if (ex != null) {
			error = ex.getMessage();
		}

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_GATEWAY.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute("ERROR_CODE", error , RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, ex.getCause().getMessage(), RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.BAD_GATEWAY);
	}
}
