package br.edu.utfpr.td.tsi.clinica.medica.persistencia.mongodb;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Paciente;

public interface PacienteRepository extends MongoRepository<Paciente, String>{
	
	Optional<Paciente> findOneByEmail(String email);
	
	Optional<Paciente> findOneByCpf(String cpf);
	
	Optional<Paciente> findByCpfOrEmail(String termo);
	
	@Query("{ '$or': [ { 'cpf': ?0 }, { 'email': ?0 } ] }")
	Optional<Paciente> buscarPorCpfOuEmail(String termo);	
}


