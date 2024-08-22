package com.synex.domain;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Policy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String policyName;
	
	private Date startDate;
	
	private Date endDate;
	
	private int userId;
	
	private String policyType;
	
	private int montlyPayment;
	
	private int totalPayment;
	
	private Double maximumCoverage;

    private Double minimumPremium;
    
    private int houseNumber;
    
    public Policy() {
    	
    }

	public Policy(int id, String policyName, Date startDate, Date endDate, int userId, String policyType,
			int montlyPayment, int totalPayment, Double maximumCoverage, Double minimumPremium, int houseNumber) {
		super();
		this.id = id;
		this.policyName = policyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.policyType = policyType;
		this.montlyPayment = montlyPayment;
		this.totalPayment = totalPayment;
		this.maximumCoverage = maximumCoverage;
		this.minimumPremium = minimumPremium;
		this.houseNumber = houseNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public int getMontlyPayment() {
		return montlyPayment;
	}

	public void setMontlyPayment(int montlyPayment) {
		this.montlyPayment = montlyPayment;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Double getMaximumCoverage() {
		return maximumCoverage;
	}

	public void setMaximumCoverage(Double maximumCoverage) {
		this.maximumCoverage = maximumCoverage;
	}

	public Double getMinimumPremium() {
		return minimumPremium;
	}

	public void setMinimumPremium(Double minimumPremium) {
		this.minimumPremium = minimumPremium;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	
	

	

	

	

	
    
    
	
	

}
