package br.edu.utfpr.td.tsi.clinica.medica.persistencia;

import java.util.List;
import java.util.Optional;


import br.edu.utfpr.td.tsi.clinica.medica.dominio.Paciente;
import br.edu.utfpr.td.tsi.clinica.medica.dominio.ResultadoPaginado;

public interface PacienteDao {
	
	public void cadastrar(Paciente paciente);
	
	public void remover(String id);
	
	public void atualizar(Paciente paciente);
	
	public Optional<Paciente> procurar(String id);
	
	public Optional<Paciente> procurarPorEmail(String email);
	
	public Optional<Paciente> procurarPorCpf(String cpf);
	
	public Optional<Paciente> procurarPorCpfOuEmail(String cpf);
	
	public List<Paciente> listarTodos();

	public ResultadoPaginado<Paciente> listarPacientesPaginacao(int pagina, int tamanho);
}



