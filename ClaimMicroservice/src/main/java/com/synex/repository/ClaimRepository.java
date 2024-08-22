package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer>{
	
	List<Claim> findByPolicyId(int policyId);

}
