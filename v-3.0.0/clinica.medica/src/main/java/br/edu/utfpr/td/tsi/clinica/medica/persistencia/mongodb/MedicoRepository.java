package br.edu.utfpr.td.tsi.clinica.medica.persistencia.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;

public interface MedicoRepository extends MongoRepository<Medico, String>{

}
