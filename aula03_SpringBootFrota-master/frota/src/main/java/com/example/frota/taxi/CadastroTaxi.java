package com.example.frota.taxi;

import jakarta.validation.constraints.NotBlank;

public record CadastroTaxi(
		@NotBlank
		Long id,
		String placa,
		String modelo,
		String marca,
		double cargaMaxima) {

	
	
}
