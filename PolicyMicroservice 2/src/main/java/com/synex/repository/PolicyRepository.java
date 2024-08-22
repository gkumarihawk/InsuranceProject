package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Home;
import com.synex.domain.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{
	
	
	List<Policy> findByUserId(int userId);

}
