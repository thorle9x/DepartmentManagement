/**
 * 
 */
package com.office.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

/**
 * @author bao.pham
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum HttpStatusEnum {
	/** Success */
	SUCCESS(001_000_000, "Successfully", 200),

	/** Unexpected */
	UNEXPECTED(100_000_000, "UnexpectedError", 500),

	/** Invalid */
	ENTITY_INVALID(100_000_001, "Invalid Enitty", 400),

	/** not found */
	NO_RECORD_FOUND(109_000_001, "No record found", 404);

	private int code;
	private String messageCode;
	private int httpStatus;

	HttpStatusEnum(int code, String messageCode, int httpStatus) {
		this.code = code;
		this.messageCode = messageCode;
		this.httpStatus = httpStatus;
	}
}
