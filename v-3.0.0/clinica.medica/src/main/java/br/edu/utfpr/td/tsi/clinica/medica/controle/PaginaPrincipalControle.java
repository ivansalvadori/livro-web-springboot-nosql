package br.edu.utfpr.td.tsi.clinica.medica.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaPrincipalControle {
	
	@GetMapping({"/", "paginaPrincipal"})
	public String exibirPaginagestaoMedico() {
		return "paginaPrincipal";
	}

}
