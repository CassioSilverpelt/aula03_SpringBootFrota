package com.example.frota.taxi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaxiRepository extends JpaRepository<Taxi, Long>{
	
	/*
	
	*/

}
