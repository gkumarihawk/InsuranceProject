package com.synex.domain;

import java.sql.Date;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Approval {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int approvalId;
	
	private String status;
	
	private int userId;
	
	private int houseNumber;
	
	public Approval() {
		
	}

	public Approval(int approvalId, String status, int userId, int houseNumber) {
		super();
		this.approvalId = approvalId;
		this.status = status;
		this.userId = userId;
		this.houseNumber = houseNumber;
	}

	public int getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	
	
	

}
