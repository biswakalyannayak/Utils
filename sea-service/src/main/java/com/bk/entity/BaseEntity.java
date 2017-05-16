package com.bk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@MappedSuperclass
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Column(name = "CREATED_AT")  
    @Temporal(TemporalType.TIMESTAMP)  
    private Date createdAt;  
  
    @Size(max = 20)  
    //@Column(name = "CREATED_BY", length = 20)  
    private String createdBy;  
  
    //@Column(name = "UPDATED_AT")  
    @Temporal(TemporalType.TIMESTAMP)  
    private Date updatedAt;  
  
    @Size(max = 20)  
    //@Column(name = "UPDATED_BY", length = 20)  
    private String updatedBy;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
    
}
