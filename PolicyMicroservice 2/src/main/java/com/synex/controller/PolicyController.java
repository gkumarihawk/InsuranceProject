package com.synex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Address;
import com.synex.domain.Approval;
import com.synex.domain.Policy;
import com.synex.service.AddressService;
import com.synex.service.ApprovalService;
import com.synex.service.PolicyService;

@RestController
public class PolicyController {
	
	@Autowired PolicyService policyService;
	
	@CrossOrigin
	@RequestMapping("/savePolicy")
	public Policy save(@RequestBody Policy policy) {
		return policyService.save(policy);
	}
	
	@CrossOrigin
    @RequestMapping("/policy/{userId}")
    public List<Policy> findByUserId(@PathVariable int userId){
        return policyService.findByUserId(userId);
        
    }
	
	@CrossOrigin
    @RequestMapping("/allPolicy")
	public List<Policy> findAll(){
		return policyService.findAll();
	}
	
	@CrossOrigin
	@PutMapping("/policies/{policyId}/updateMinPremium")
	public ResponseEntity<Policy> updateMinPremium(@PathVariable int policyId, @RequestBody Map<String, Double> requestData) {
	    double approvedAmount = requestData.get("approvedAmount");
	    Policy updatedPolicy = policyService.updateMinPremium(policyId, approvedAmount);
	    return ResponseEntity.ok(updatedPolicy);
	}
}


