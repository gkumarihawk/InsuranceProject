package com.synex.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Home {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int homeId;
	
	private int houseNumber;
	
	private Date built;
	
	private Date bought;
	
	@Column(length = 2000)
    private String description;

	public Home(int homeId, int houseNumber, Date built, Date bought, String description) {
		super();
		this.homeId = homeId;
		this.houseNumber = houseNumber;
		this.built = built;
		this.bought = bought;
		this.description = description;
	}

	public int getHomeId() {
		return homeId;
	}

	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Date getBuilt() {
		return built;
	}

	public void setBuilt(Date built) {
		this.built = built;
	}

	public Date getBought() {
		return bought;
	}

	public void setBought(Date bought) {
		this.bought = bought;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
	

}
