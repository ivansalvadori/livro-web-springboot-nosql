package br.edu.utfpr.td.tsi.clinica.medica.persistencia.mongodb;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;
import br.edu.utfpr.td.tsi.clinica.medica.persistencia.MedicoDao;

@Profile("mongodb")
@Component
public class MongoDbMedicoDao implements MedicoDao {

	private MedicoRepository medicoRepository;

	@Autowired
	public MongoDbMedicoDao(MedicoRepository medicoRepository) {
		super();
		this.medicoRepository = medicoRepository;
	}

	@Override
	public void gravar(Medico medico) {
		medico.setId(UUID.randomUUID().toString());
		medicoRepository.save(medico);
	}

	@Override
	public void atualizar(Medico medicoAtualizado) {
		medicoRepository.save(medicoAtualizado);
	}

	@Override
	public void remover(String id) {
		medicoRepository.deleteById(id);
	}

	@Override
	public Medico encontrar(String id) {
		return medicoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Medico> listarTodos() {
		return medicoRepository.findAll();
	}
}