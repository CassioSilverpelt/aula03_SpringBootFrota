package com.example.frota.marca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;

@Service
public class MarcaService {
	@Autowired
	private MarcaRepository marcaRepository;
	
	public Marca salvar(Marca marca) {
		return marcaRepository.save(marca);
	}
	
	public List<Marca> procurarTodos(){
		return marcaRepository.findAll(Sort.by("nome").ascending());
	}
	
	public void apagarPorId(Long id) {
		marcaRepository.deleteById(id);
	}
	
	public Marca procurarPorId (@NotNull Long id) {
		return marcaRepository.getReferenceById(id);
	}
}