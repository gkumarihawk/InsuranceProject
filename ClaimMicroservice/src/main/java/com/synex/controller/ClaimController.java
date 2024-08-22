package com.synex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Claim;
import com.synex.service.ClaimService;

@RestController
public class ClaimController {
	
	@Autowired ClaimService claimService;
	
	@CrossOrigin
	@RequestMapping("/saveClaim")
	public Claim saveClaim(@RequestBody Claim claim) {
		return claimService.save(claim);
	}
	
	@CrossOrigin
	@RequestMapping("/claims")
	public List<Claim> findAll(){
		return claimService.findAll();
	}
	
	@CrossOrigin
	@RequestMapping("/claims/{policyId}")
	public List<Claim> findByApprovalId(@PathVariable int policyId){
		return claimService.findByPolicyId(policyId);
	}
	
	@CrossOrigin
    @PutMapping("/claims/{id}/complete")
    public ResponseEntity<?> completeClaim(@PathVariable int id, @RequestBody Claim request) {
        try {
            Claim completedClaim = claimService.completeClaim(id, request.getApprovedAmount());
            return ResponseEntity.ok(completedClaim);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to complete claim: " + e.getMessage());
        }
    }
    
	@CrossOrigin
    @PutMapping("/claims/{id}/updateApprovedAmount")
    public ResponseEntity<?> updateApprovedAmount(@PathVariable int id, @RequestBody Map<String, Double> requestData) {
        double approvedAmount = requestData.get("approvedAmount");
        try {
            Claim updatedClaim = claimService.updateApprovedAmount(id, approvedAmount);
            return ResponseEntity.ok(updatedClaim);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update approved amount: " + e.getMessage());
        }
    }

}
