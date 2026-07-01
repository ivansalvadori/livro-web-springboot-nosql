package br.edu.utfpr.td.tsi.clinica.medica.controle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Paciente;
import br.edu.utfpr.td.tsi.clinica.medica.dominio.ResultadoPaginado;
import br.edu.utfpr.td.tsi.clinica.medica.persistencia.PacienteDao;
import br.edu.utfpr.td.tsi.clinica.medica.persistencia.mongodb.PacienteRepository;

@Controller
public class PacienteControle {

	private PacienteDao pacienteDao;

	// Evitar o uso direto do MongoRepository no controller,
	// pois isso acopla a camada de controle à tecnologia de persistência.
	// Preferencialmente utilizar uma camada de abstração (DAO).
	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	public PacienteControle(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	@GetMapping("/cadastrarPaciente")
	public String exibirPaginaCadastroMedico(Model model) {
		return "cadastroPaciente";
	}

	@PostMapping("/cadastrarPaciente")
	public String receberCadastroPaciente(Paciente paciente) {
		pacienteDao.cadastrar(paciente);
		return "redirect:listarTodosPacientes";
	}

	@GetMapping("/listarTodosPacientes")
	public String exibirPaginaListagemTodosPacientes(Model model) {
		List<Paciente> pacientes = pacienteDao.listarTodos();
		model.addAttribute("pacientes", pacientes);
		return "listagemTodosPacientes";
	}

	@GetMapping("/listarTodosPacientesPaginacao")
	public String exibirPaginaListagemTodosPacientesPaginacao(@RequestParam(defaultValue = "0") int pagina,
			@RequestParam(defaultValue = "10") int tamanho, Model model) {

		ResultadoPaginado<Paciente> resultado = pacienteDao.listarPacientesPaginacao(pagina, tamanho);
		model.addAttribute("resultado", resultado);
		return "listagemTodosPacientesPaginacao";
	}

	@GetMapping("/listarTodosPacientesPaginacaoMongo")
	public String exibirPaginaListagemTodosPacientesPaginacaoMongo(
			@RequestParam(defaultValue = "0") int pagina,
			@RequestParam(defaultValue = "10") int tamanho, Model model) {

		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("nome").ascending());
		Page<Paciente> pacientes = pacienteRepository.findAll(pageable);
		model.addAttribute("pacientes", pacientes);
		return "listagemTodosPacientesPaginacaoMongo";
	}

	@GetMapping("/editarPaciente")
	public String exibirPaginaEdicaoPaciente(Model model, String id) {
		Paciente paciente = pacienteDao.procurar(id).orElse(null);
		model.addAttribute("paciente", paciente);
		return "editarPaciente";
	}

	@PostMapping("/editarPaciente")
	public String editarPaciente(String id, Paciente paciente) {
		pacienteDao.atualizar(paciente);
		return "redirect:listarTodosPacientes";
	}

	@GetMapping("/removerPaciente")
	public String removerpaciente(String id) {
		pacienteDao.remover(id);
		return "redirect:listarTodosPacientes";
	}

	@GetMapping("/buscarPacientePorEmailOuCpf")
	public String buscarPacientePorEmailOuCpf(Model model, String termo) {
		List<Paciente> pacientes = new ArrayList<Paciente>();
		pacienteDao.procurarPorCpfOuEmail(termo).ifPresent(paciente -> {
			pacientes.add(paciente);
		});
		model.addAttribute("pacientes", pacientes);
		return "listagemTodosPacientes";
	}

}
