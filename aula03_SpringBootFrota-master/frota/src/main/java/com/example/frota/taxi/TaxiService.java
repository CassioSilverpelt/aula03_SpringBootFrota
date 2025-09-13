package com.example.frota.taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxiService {
	
	@Autowired
	private TaxiRepository repository;
	
	public Taxi save(Taxi taxi) {
		
		return repository.save(taxi);
		
	}
}
