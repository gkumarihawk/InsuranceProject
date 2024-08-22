package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.CityAssessment;
import com.synex.domain.Document;

@Repository
public interface CityAssessmentRepository extends JpaRepository<CityAssessment, Integer>{
	
	List<CityAssessment> findByProvince(String province);

}
