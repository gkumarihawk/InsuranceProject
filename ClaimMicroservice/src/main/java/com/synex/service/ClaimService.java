package com.synex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Claim;
import com.synex.repository.ClaimRepository;

@Service
public class ClaimService {
	
	@Autowired ClaimRepository claimRepository;
	
	public Claim save(Claim claim) {
		return claimRepository.save(claim);
	}
	
	public List<Claim> findAll(){
		return claimRepository.findAll();
	}
	
	public List<Claim> findByPolicyId(int policyId){
		return claimRepository.findByPolicyId(policyId);
	}
	
	public Claim completeClaim(int id, double approvedAmount) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + id));

        claim.setStatus("completed");
        claim.setApprovedAmount(approvedAmount); // Update approvedAmount, assuming this is the correct field to update

        // Save updated claim to repository
        return claimRepository.save(claim);
    }
    
    public Claim updateApprovedAmount(int id, double approvedAmount) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claim.setApprovedAmount(approvedAmount);
            return claimRepository.save(claim); // Save the updated claim
        } else {
            throw new RuntimeException("Claim not found with id: " + id);
        }
    }

}
