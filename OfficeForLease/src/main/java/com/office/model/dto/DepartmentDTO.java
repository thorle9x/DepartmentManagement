package com.office.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = 8943172320041071513L;

	private long id;

	private Long departmentId;

	private String name;

	private String owner;

	private String address;

	private Long totalRoom;

	private Float invesment;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp startDate;

	private Boolean active;

}
