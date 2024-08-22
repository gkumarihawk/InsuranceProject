package com.synex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Insurance;
import com.synex.domain.Policy;
import com.synex.repository.InsuranceRepository;
import com.synex.repository.PolicyRepository;

@Service
public class InsuranceService {
	
@Autowired InsuranceRepository insuranceRepository;
	
	public Insurance save(Insurance insurance) {
		return insuranceRepository.save(insurance);
		
	}
	
	public Insurance findById(int id) {
		Optional<Insurance> optInsurance = insuranceRepository.findById(id);
		if(optInsurance.isPresent()) {
		return optInsurance.get();
		}
		return null; 
	}
	
	public List<Insurance> findAll(){
		return insuranceRepository.findAll();
	}

}
