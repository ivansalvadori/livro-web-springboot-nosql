package br.edu.utfpr.td.tsi.clinica.medica.persistencia;

import java.util.List;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;

public interface MedicoDao {
	public void gravar(Medico medico);
	public void atualizar(Medico medicoAtualizado);	
	public void remover(String id);
	public Medico encontrar(String id);
	public List<Medico> listarTodos();
}
