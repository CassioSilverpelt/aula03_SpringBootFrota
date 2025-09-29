package com.example.frota.taxi;

import com.example.frota.marca.Marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "taxi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Taxi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "taxi_id")
	private Long id;
	private String placa;
	private String modelo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_id", referencedColumnName = "marca_id")
	private Marca marca;
	private double cargaMaxima;
	private int ano;

	public Taxi(CadastroTaxi dados, Marca marca) {
		this.modelo = dados.modelo();
		this.placa = dados.placa();
		this.cargaMaxima = dados.cargaMaxima();
		this.marca = marca;
		this.ano = dados.ano();
	}
	
}
