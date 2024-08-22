package com.synex.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.CityAssessment;
import com.synex.domain.InsuranceAssessment;
import com.synex.repository.CityAssessmentRepository;
import com.synex.repository.InsuranceAssessmentRepository;

@Service
public class AssessmentService {
	
	@Autowired InsuranceAssessmentRepository insuranceRepository;
	
	@Autowired CityAssessmentRepository cityRepository;
	
	 public void saveInsurance(InsuranceAssessment insurance) {
	        insuranceRepository.save(insurance);
	    }
	 
	 public List<CityAssessment> getCitiesByProvince(String province) {
	        return cityRepository.findByProvince(province);
	    }
	
	 public List<String> getProvinces() {
	    List<CityAssessment> cities = cityRepository.findAll();
	    Set<String> provinces = new HashSet<>();

	    for (CityAssessment city : cities) {
	        provinces.add(city.getProvince());
	    }

	    return new ArrayList<>(provinces);
	}
	
	

   


}
