package com.example.frota.taxi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;

@Service
public class TaxiService {
	
	@Autowired
	private TaxiRepository taxiRepository;
	
	public Taxi salvar(Taxi taxi) {
		return taxiRepository.save(taxi);
	}
	
	public List<Taxi> procurarTodos(){
		return taxiRepository.findAll(Sort.by("modelo").ascending());
	}
	
	public void apagarPorID(Long id) {
		taxiRepository.deleteById(id);
	}
	
	public Taxi procurarPorId(@NotNull Long id) {
		return taxiRepository.getReferenceById(id);
	}
	
}
