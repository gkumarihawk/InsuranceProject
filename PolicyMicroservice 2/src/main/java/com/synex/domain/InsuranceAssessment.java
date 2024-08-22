package com.synex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class InsuranceAssessment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private int salary;
    private int noOfMembers;

    @ManyToOne
    private CityAssessment city;
    
    public InsuranceAssessment() {
    	
    }

	public InsuranceAssessment(int id, String name, int age, int salary, int noOfMembers, CityAssessment city) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.noOfMembers = noOfMembers;
		this.city = city;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getNoOfMembers() {
		return noOfMembers;
	}

	public void setNoOfMembers(int noOfMembers) {
		this.noOfMembers = noOfMembers;
	}

	public CityAssessment getCity() {
		return city;
	}

	public void setCity(CityAssessment city) {
		this.city = city;
	}
    
    

}
