package br.edu.utfpr.td.tsi.clinica.medica.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Especialidade;
import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;
import br.edu.utfpr.td.tsi.clinica.medica.persistencia.MedicoDao;

@Controller
public class MedicoControle {

	@Autowired
	private MedicoDao medicoDAO;

	@GetMapping("/cadastrarMedico")
	public String exibirPaginaCadastroMedico(Model model) {
		model.addAttribute("especialidades", Especialidade.values());
		return "cadastroMedico";
	}

	@PostMapping("/cadastrarMedico")
	public String receberCadastroMedico(Medico medico) {
		medicoDAO.gravar(medico);
		return "cadastroMedicoSucesso";
	}

	@GetMapping("/listarTodosMedicos")
	public String exibirPaginaListagemTodosMedicos(Model model) {
		List<Medico> medicos = medicoDAO.listarTodos();
		model.addAttribute("medicos", medicos);
		return "listagemTodosMedicos";
	}

	@GetMapping("/editarMedico")
	public String exibirPaginaEdicaoMedico(Model model, String id) {
		Medico medico = medicoDAO.encontrar(id);
		model.addAttribute("especialidades", Especialidade.values());
		model.addAttribute("medico", medico);
		return "editarMedico";
	}

	@PostMapping("/editarMedico")
	public String editarMedico(String id, Medico medico) {
		medicoDAO.atualizar(medico);
		return "redirect:listarTodosMedicos";
	}

	@GetMapping("/removerMedico")
	public String removerMedico(String id) {
		medicoDAO.remover(id);
		return "redirect:listarTodosMedicos";
	}

}
