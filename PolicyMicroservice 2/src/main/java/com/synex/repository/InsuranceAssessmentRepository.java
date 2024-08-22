package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.CityAssessment;
import com.synex.domain.InsuranceAssessment;

@Repository
public interface InsuranceAssessmentRepository extends JpaRepository<InsuranceAssessment, Integer>{
	
	

}
