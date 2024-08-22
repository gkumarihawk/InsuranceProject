package com.synex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Policy;
import com.synex.domain.Address;
import com.synex.repository.PolicyRepository;
import com.synex.repository.AddressRepository;

@Service
public class AddressService {
	
@Autowired AddressRepository userRepository;

	public List<Address> findAll() {
		return userRepository.findAll();
	}
	
	public Address save(Address address) {
		return userRepository.save(address);
	}
	
	public Address findAddress(int houseNumber) {
		return userRepository.findByHouseNumber(houseNumber);
	}



}
