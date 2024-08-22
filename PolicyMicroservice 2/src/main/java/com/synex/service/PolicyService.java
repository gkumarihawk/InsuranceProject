package com.synex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synex.domain.Policy;
import com.synex.repository.PolicyRepository;

@Service
public class PolicyService {
	
	@Autowired PolicyRepository policyRepository;
	
	public Policy save(Policy policy) {
		return policyRepository.save(policy);
		
	}
	
	public Policy findById(int id) {
		Optional<Policy> optPolicy = policyRepository.findById(id);
		if(optPolicy.isPresent()) {
		return optPolicy.get();
		}
		return null; 
	}
	
	public List<Policy> findAll(){
		return policyRepository.findAll();
	}
	
	public List<Policy> findByUserId(int userId){
		return policyRepository.findByUserId(userId);
	}
	
	public Policy updateMinPremium(int policyId, double approvedAmount) {
	    Optional<Policy> optionalPolicy = policyRepository.findById(policyId);
	    if (optionalPolicy.isPresent()) {
	        Policy policy = optionalPolicy.get();
	        double newMinPremium = policy.getMinimumPremium() - approvedAmount;
	        policy.setMinimumPremium(newMinPremium);
	        return policyRepository.save(policy);
	    } else {
	        throw new RuntimeException("Policy not found with id: " + policyId);
	    }
	}


}
