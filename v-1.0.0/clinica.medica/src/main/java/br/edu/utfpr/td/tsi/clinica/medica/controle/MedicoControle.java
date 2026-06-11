package br.edu.utfpr.td.tsi.clinica.medica.controle;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Especialidade;
import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;
import br.edu.utfpr.td.tsi.clinica.medica.persistencia.BancoDeDados;


@Controller
public class MedicoControle {
	
	private BancoDeDados bancoDeDados = new BancoDeDados();
	
	@GetMapping("/gestaoMedicos")
	public String exibirPaginagestaoMedico() {
		return "gestaoMedicos";
	}
	
	@GetMapping("/cadastrarMedico")
	public String exibirPaginaCadastroMedico(Model model) {
		model.addAttribute("especialidades", Especialidade.values());
		return "cadastroMedico";
	}
	
	@PostMapping("/cadastrarMedico")
	public String receberCadastroMedico(Medico medico) {
		bancoDeDados.gravar(medico);
		return "cadastroMedicoSucesso";
	}
	
	@GetMapping("/listarTodosMedicos")
	public String exibirPaginaListagemTodosMedicos(Model model) {
		List<Medico> medicos =  bancoDeDados.listarTodos();
		model.addAttribute("medicos", medicos);
		return "listagemTodosMedicos";
	}
	
	@GetMapping("/editarMedico")
	public String exibirPaginaEdicaoMedico(Model model, String cpf) {
		Medico medico = bancoDeDados.encontrar(cpf);
		model.addAttribute("especialidades", Especialidade.values());
		model.addAttribute("medico", medico);
		return "editarMedico";
	}
	
	@PostMapping("/editarMedico")
	public String editarMedico(Medico medico) {
    	bancoDeDados.atualizar(medico);
		return "redirect:listarTodosMedicos";
	}
	
	@GetMapping("/removerMedico")
	public String removerMedico(String cpf) {
		bancoDeDados.remover(cpf);
		return "redirect:listarTodosMedicos";
	}
	
} 


