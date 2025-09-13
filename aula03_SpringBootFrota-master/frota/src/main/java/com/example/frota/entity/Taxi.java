package com.example.frota.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Taxi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String placa;
	String modelo;
	String marca;
	
	public Taxi () {
		
	}
	
}
