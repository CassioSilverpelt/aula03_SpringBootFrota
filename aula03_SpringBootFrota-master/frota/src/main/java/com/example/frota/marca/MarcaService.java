package com.example.frota.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {
	@Autowired
	private MarcaRepository repository;
	
	public Marca save(Marca marca) {
		return repository.save(marca);
	}
	//CRUD
}