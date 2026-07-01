package br.edu.utfpr.td.tsi.clinica.medica.persistencia.mysql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;
import br.edu.utfpr.td.tsi.clinica.medica.persistencia.MedicoDao;

@Profile("mysql")
@Component
public class MySqlMedicoDao implements MedicoDao {

	public MySqlMedicoDao() {
		System.out.println("Inicializando MySQLMedicoDao");
	}

	@Override
	public void gravar(Medico medico) {
		// Aqui vai o código para persistir dados em MySQL.
		System.out.println("gravando dados em MySQL...");
	}

	@Override
	public void atualizar(Medico medicoAtualizado) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remover(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Medico encontrar(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medico> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
