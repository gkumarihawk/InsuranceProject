package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Home;

@Repository
public interface HomeRepository extends JpaRepository<Home, Integer>{
	
	Home findByHouseNumber(int houseNumber);

}
