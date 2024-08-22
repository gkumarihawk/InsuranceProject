package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer>{
	

}
