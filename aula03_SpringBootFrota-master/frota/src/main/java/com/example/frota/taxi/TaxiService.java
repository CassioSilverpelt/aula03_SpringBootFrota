package com.example.frota.taxi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.frota.marca.Marca;
import com.example.frota.marca.MarcaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaxiService {
	
	@Autowired
	private TaxiRepository taxiRepository;
	
	@Autowired
	private MarcaService marcaService;
	
	@Autowired
	private TaxiMapper taxiMapper;
	
	public List<Taxi> procurarTodos(){
		return taxiRepository.findAll(Sort.by("modelo").ascending());
	}
	
	public void apagarPorID(Long id) {
		taxiRepository.deleteById(id);
	}
	
	public Optional<Taxi> procurarPorId(Long id) {
	    return taxiRepository.findById(id);
	}
	
	public Taxi salvarOuAtualizar(AtualizacaoTaxi dto) {
        // Valida se a marca existe
        Marca marca = marcaService.procurarPorId(dto.marcaId())
            .orElseThrow(() -> new EntityNotFoundException("Marca não encontrada com ID: " + dto.marcaId()));
        
        if (dto.id() != null) {
            // atualizando Busca existente e atualiza
            Taxi existente = taxiRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado com ID: " + dto.id()));
            
            taxiMapper.updateEntityFromDto(dto, existente);
            existente.setMarca(marca); // Atualiza a marca
            
            return taxiRepository.save(existente);
        } else {
            // criando Novo caminhão
            Taxi novoTaxi = taxiMapper.toEntityFromAtualizacao(dto);
            novoTaxi.setMarca(marca); // Define a marca completa
            
            return taxiRepository.save(novoTaxi);
        }
    }
	
}
