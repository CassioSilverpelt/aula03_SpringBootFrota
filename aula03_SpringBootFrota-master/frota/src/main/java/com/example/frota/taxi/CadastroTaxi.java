package com.example.frota.taxi;

import com.example.frota.marca.Marca;

import jakarta.validation.constraints.NotBlank;

public record CadastroTaxi(
		@NotBlank
		String modelo,
		String placa,
		Marca marca,
		double cargaMaxima,
		int ano) {
}
