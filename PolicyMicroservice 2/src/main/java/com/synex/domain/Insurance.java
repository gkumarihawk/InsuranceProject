package com.synex.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name="insurances")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Insurance {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String type;

    @Column(length = 2000)
    private String description;

    private Double maximumCoverage;

    private Double minimumPremium;
    
    public Insurance() {
    	
    }
    
    public Insurance( String name, String type, String description, Double maximumCoverage,
			Double minimumPremium) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
		this.maximumCoverage = maximumCoverage;
		this.minimumPremium = minimumPremium;
	}

	@Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", maximumCoverage=" + maximumCoverage +
                ", minimumPremium=" + minimumPremium +
                '}';
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
    
    
	

}
