package br.edu.utfpr.td.tsi.clinica.medica.persistencia.mongodb;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Paciente;
import br.edu.utfpr.td.tsi.clinica.medica.dominio.ResultadoPaginado;
import br.edu.utfpr.td.tsi.clinica.medica.persistencia.PacienteDao;

@Profile("mongodb")
@Component
public class MongoDbPacienteDao implements PacienteDao {

	private PacienteRepository pacienteRepository;

	@Autowired
	public MongoDbPacienteDao(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	@Override
	public void cadastrar(Paciente paciente) {
		paciente.setId(UUID.randomUUID().toString());
		pacienteRepository.save(paciente);
	}

	@Override
	public void remover(String id) {
		pacienteRepository.deleteById(id);
	}

	@Override
	public void atualizar(Paciente paciente) {
		pacienteRepository.save(paciente);
	}

	@Override
	public Optional<Paciente> procurar(String id) {
		return pacienteRepository.findById(id);
	}

	@Override
	public Optional<Paciente> procurarPorEmail(String email) {
		return pacienteRepository.findOneByEmail(email);
	}

	@Override
	public Optional<Paciente> procurarPorCpf(String cpf) {
		return pacienteRepository.findOneByCpf(cpf);
	}

	@Override
	public List<Paciente> listarTodos() {
		return pacienteRepository.findAll();
	}

	@Override
	public Optional<Paciente> procurarPorCpfOuEmail(String termo) {
		return pacienteRepository.buscarPorCpfOuEmail(termo);
	}

	@Override
	public ResultadoPaginado<Paciente> listarPacientesPaginacao(int pagina, int tamanho) {
		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("nome").ascending());

		Page<Paciente> pacientes = pacienteRepository.findAll(pageable);

		ResultadoPaginado<Paciente> resultado = new ResultadoPaginado<>();

		resultado.setItens(pacientes.getContent());
		resultado.setPaginaAtual(pacientes.getNumber());
		resultado.setTamanhoPagina(pacientes.getSize());
		resultado.setTotalPaginas(pacientes.getTotalPages());
		resultado.setTotalRegistros(pacientes.getTotalElements());
		resultado.setTemAnterior(pacientes.hasPrevious());
		resultado.setTemProxima(pacientes.hasNext());

		return resultado;

	}

}
