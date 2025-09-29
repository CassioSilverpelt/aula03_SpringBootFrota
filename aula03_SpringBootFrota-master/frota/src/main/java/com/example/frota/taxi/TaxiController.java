package com.example.frota.taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.frota.marca.MarcaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/taxi")
public class TaxiController {
	
	@Autowired
	private TaxiService taxiService;
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	public String carregaPaginaFormulario (Model model) {
		model.addAttribute("ListaVeiculos", taxiService.procurarTodos());
		return "taxi/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario (Long id, Model model) {
		model.addAttribute("marcas", marcaService.procurarTodos());
		if (id != null) {
			var taxi = taxiService.procurarPorId(id);
			model.addAttribute("taxi", taxi);
		}
		return "taxi/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastrar (@Valid CadastroTaxi dados) {
		var marca = marcaService.procurarPorId(dados.marca().getId());
		var taxi = new Taxi(dados, marca);
		taxiService.salvar(taxi);
		return "redirect:taxi";
	}
	
	@DeleteMapping
	@Transactional
	public String excluir (Long id) {
		taxiService.apagarPorID(id);
		return "redirect:taxi";
	}
	
}
