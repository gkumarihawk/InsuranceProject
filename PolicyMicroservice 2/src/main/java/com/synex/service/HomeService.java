package com.synex.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Home;
import com.synex.domain.Policy;
import com.synex.repository.HomeRepository;
import com.synex.repository.PolicyRepository;

@Service
public class HomeService {
	
@Autowired HomeRepository homeRepository;
	
	public Home save(Home home) {
		return homeRepository.save(home);
		
	}
	
	public Home findById(int id) {
		Optional<Home> optHome = homeRepository.findById(id);
		if(optHome.isPresent()) {
		return optHome.get();
		}
		return null; 
	}

}
