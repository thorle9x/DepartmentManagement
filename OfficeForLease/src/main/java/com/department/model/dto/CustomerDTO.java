package com.department.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO extends AbstractDTO implements Serializable {

	private static final long serialVersionUID = -2669973140542920610L;

	private long id;

	private String name;

	private Integer age;

	private String address1;

	private String address2;

	private String phoneNumber1;

	private String phoneNumber2;

	private String email;

	private Boolean isRenter;

	private String type;

	private String note;
	
	private String reference;
}

