package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer>{
	
	List<Approval> findByUserId(int userId);
	
	Approval findByHouseNumber(int houseNumber);
	
	

}
