package com.example.frota.taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/taxi")
public class TaxiController {
	
	@Autowired
	private TaxiRepository repository;
	
	@GetMapping("/formulario")
	public String carregaPagFormulario(/*Long id, Model model*/) {
		return "caminhao/formulario";
	}
	
	@GetMapping("/listagem")
	public String carregaPagListagem(/*Long id, Model model*/) {
		return "caminhao/listagem";
	}
	
	@PostMapping
	@Transactional
	public String cadastrar(CadastroTaxi dados) {
		//Taxi taxi = new Taxi(dados);
		return "";
	}
}
