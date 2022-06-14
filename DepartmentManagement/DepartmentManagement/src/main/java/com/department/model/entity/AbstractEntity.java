package com.department.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.department.common.DateUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7134198290800182161L;
	public static final String[] IGNORE_PROPS = { "updatedDate", "updatedBy", "createdDate", "createdBy" };
	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name = "UPDATED_BY", length = 50)
	private String updatedBy;

	@Column(name = "CREATED_DATE", nullable = false)
	private Timestamp createdDate;

	@Column(name = "CREATED_BY", length = 50, nullable = false)
	private String createdBy;

	@PrePersist
	void createdAt() {
		this.updatedDate = this.createdDate = DateUtils.getCurrentTimestamp();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedDate = DateUtils.getCurrentTimestamp();
	}
}
