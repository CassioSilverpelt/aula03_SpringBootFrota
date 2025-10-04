package com.example.frota.taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.frota.marca.MarcaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/taxi")
public class TaxiController {
	
	@Autowired
	private TaxiService taxiService;
	
	@Autowired
	private MarcaService marcaService;
	
	@Autowired
	private TaxiMapper taxiMapper;
	
	@GetMapping ("/formulario/{id}")    
	public String carregaPaginaFormulario (@PathVariable("id") Long id, Model model,
			RedirectAttributes redirectAttributes) {
		AtualizacaoTaxi dto;
		try {
			if(id != null) {
				Taxi taxi = taxiService.procurarPorId(id)
						.orElseThrow(() -> new EntityNotFoundException("Taxi não encontrado"));
				model.addAttribute("marcas", marcaService.procurarTodos());
				//mapear Taxi para AtualizacaoTaxi
				dto = taxiMapper.toAtualizacaoDto(taxi);
				model.addAttribute("taxi", dto);
			}
			return "caminhao/formulario";
		} catch (EntityNotFoundException e) {
			//resolver erros
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/taxi";
		}
	}
	
	//Novo GetMapping com DTO e Mapper
	@GetMapping("/formulario")
    public String mostrarFormulario(@RequestParam(required = false) Long id, Model model) {
		AtualizacaoTaxi dto;
        if (id != null) {
            //edição: Carrega dados existentes
            Taxi taxi = taxiService.procurarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Taxi não encontrado"));
            dto = taxiMapper.toAtualizacaoDto(taxi);
        } else {
            // criação: DTO vazio
            dto = new AtualizacaoTaxi(null, "", "", null, null, null);
        }
        model.addAttribute("taxi", dto);
        model.addAttribute("marcas", marcaService.procurarTodos());
        return "taxi/formulario";
    }
	
	@PostMapping("/salvar")
    public String salvar(@ModelAttribute("taxi") @Valid AtualizacaoTaxi dto,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        Model model) {
		if (result.hasErrors()) {
	        // Recarrega dados necessários para mostrar erros
	        model.addAttribute("marcas", marcaService.procurarTodos());
	        return "taxi/formulario";
	    }
	    
	    try {
	        Taxi taxiSalvo = taxiService.salvarOuAtualizar(dto);
	        
	        String mensagem = dto.id() != null
	            ? "Taxi '" + taxiSalvo.getPlaca() + "' atualizado com sucesso!"
	            : "Taxi '" + taxiSalvo.getPlaca() + "' criado com sucesso!";
	        
	        redirectAttributes.addFlashAttribute("message", mensagem);
	        return "redirect:/taxi";
	        
	    } catch (EntityNotFoundException e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	        return "redirect:/taxi/formulario" + (dto.id() != null ? "?id=" + dto.id() : "");
	    }
	}
	
	@GetMapping("/delete/{id}")
	@Transactional
	public String deleteTutorial(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			taxiService.apagarPorID(id);
			redirectAttributes.addFlashAttribute("message", "O taxi " + id + " foi apagado!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/caminhao";
	}
	
}
