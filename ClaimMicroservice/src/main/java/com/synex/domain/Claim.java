package com.synex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Claim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int policyId;
	
	private String policyType;
	
	private String reason;
	
	private Double claimAmount;
	
	private Double maxCoverage;
	
	private Double minPremium;
	
	private String policyName;
	
	private String status;
	
	private Double approvedAmount;
	
	public Claim() {
		
	}

	
	
	public Claim(long id,  int policyId, String policyType, String reason, Double claimAmount,
			Double maxCoverage, Double minPremium, String policyName, String status, Double approvedAmount) {
		super();
		this.id = id;
		this.policyId = policyId;
		this.policyType = policyType;
		this.reason = reason;
		this.claimAmount = claimAmount;
		this.maxCoverage = maxCoverage;
		this.minPremium = minPremium;
		this.policyName = policyName;
		this.status = status;
		this.approvedAmount = approvedAmount;
	}



	public Claim(String reason, double claimAmount, String claimStatus) {
        this.reason = reason;
        this.claimAmount = claimAmount;
        this.status = claimStatus;
    }


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Double getMaxCoverage() {
		return maxCoverage;
	}

	public void setMaxCoverage(Double maxCoverage) {
		this.maxCoverage = maxCoverage;
	}

	public Double getMinPremium() {
		return minPremium;
	}

	public void setMinPremium(Double minPremium) {
		this.minPremium = minPremium;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public Double getApprovedAmount() {
		return approvedAmount;
	}



	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	
	


}
