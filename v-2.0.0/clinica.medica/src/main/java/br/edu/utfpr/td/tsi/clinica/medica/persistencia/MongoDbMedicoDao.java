package br.edu.utfpr.td.tsi.clinica.medica.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Especialidade;
import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;

@Profile("mongodb")
@Component
public class MongoDbMedicoDao implements MedicoDao{
	
	   private final MongoClient mongoClient;
	   private final MongoDatabase database;
	   private final MongoCollection<Document> collection;

	   public MongoDbMedicoDao() {
	      this.mongoClient = MongoClients.create("mongodb://localhost:27017");
	      this.database = mongoClient.getDatabase("clinica-medica");
	      this.collection = database.getCollection("medico");
	   }

	   @Override
	   public void gravar(Medico medico) {
	      Document doc = toDocument(medico);
	      collection.insertOne(doc);
	   }

	   @Override
	   public void atualizar(Medico medicoAtualizado) {
	      Document filtro = new Document("_id", medicoAtualizado.getId());
	      Document novo = new Document("$set", toDocument(medicoAtualizado));
	      collection.updateOne(filtro, novo);
	   }

	   @Override
	   public void remover(String id) {
	      collection.deleteOne(new Document("_id", id));
	   }

	   @Override
	   public Medico encontrar(String id) {
	      Document doc = collection.find(new Document("_id", id)).first();
	      return doc != null ? fromDocument(doc) : null;
	   }

	   @Override
	   public List<Medico> listarTodos() {
	      List<Medico> lista = new ArrayList<>();

	      for (Document doc : collection.find()) {
	         lista.add(fromDocument(doc));
	      }

	      return lista;
	   }

	   private Document toDocument(Medico medico) {
	      return new Document()
	         .append("_id", medico.getId())
	         .append("nome", medico.getNome())
	         .append("email", medico.getEmail())
	         .append("cpf", medico.getCpf())
	         .append("crm", medico.getCrm())
	         .append("especialidades",
	            medico.getEspecialidades() != null
	               ? medico.getEspecialidades().stream().map(Enum::name).toList()
	               : new ArrayList<>());
	   }

	   private Medico fromDocument(Document doc) {
	      Medico medico = new Medico();
	      medico.setId(doc.getString("_id"));
	      medico.setNome(doc.getString("nome"));
	      medico.setEmail(doc.getString("email"));
	      medico.setCpf(doc.getString("cpf"));
	      medico.setCrm(doc.getString("crm"));

	      @SuppressWarnings("unchecked")
	      List<String> esp = (List<String>) doc.get("especialidades");
	      if (esp != null) {
	         medico.setEspecialidades(esp.stream().map(Especialidade::valueOf).toList());
	      }
	      return medico;
	   }
	}