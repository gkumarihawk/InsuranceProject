package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Address;
import com.synex.domain.Policy;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	Address findByHouseNumber(int houseNumber);
	
	

}
