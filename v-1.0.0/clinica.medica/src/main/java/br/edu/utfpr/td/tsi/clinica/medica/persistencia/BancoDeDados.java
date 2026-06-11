package br.edu.utfpr.td.tsi.clinica.medica.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.clinica.medica.dominio.Medico;

public class BancoDeDados {
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	public void gravar(Medico medico) {
		this.medicos.add(medico);
	}
	
	public void atualizar(Medico medicoAtualizado) {
		String cpf = medicoAtualizado.getCpf();
	    for (int i = 0; i < medicos.size(); i++) {
	        Medico medico = medicos.get(i);
	        if (medico.getCpf().equalsIgnoreCase(cpf)) {
	            medicos.set(i, medicoAtualizado);
	            return;
	        }
	    }
	}
	
	public void remover(String cpf) {
	    medicos.removeIf(
	        medico -> medico.getCpf().equalsIgnoreCase(cpf)
	    );
	}
	
	public Medico encontrar(String cpf) {
	    for (Medico medico : medicos) {
	        if (medico.getCpf().equalsIgnoreCase(cpf)) {
	            return medico;
	        }
	    }
	    return null;
	}
	
	public List<Medico> listarTodos(){
		return this.medicos;
	}
}
