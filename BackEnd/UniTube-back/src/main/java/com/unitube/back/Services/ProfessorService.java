package com.unitube.back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitube.back.Entities.Professor;
import com.unitube.back.Repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public List<Professor> listarProfessores() {
		return professorRepository.findAll();
	}
	
	public Optional<Professor> procurarProfessor(Long id) {
		return professorRepository.findById(id);
	}
	
	public Professor criarProfessor(Professor professor) {
		professorRepository.save(professor);
		return professor;
	}
	
	public Professor atualizarProfessor(Long id, Professor professorNovo) {
		Professor professorAntigo = professorRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Professor não encontrado."));
		
		if(professorNovo.getNome() != null) {
			professorAntigo.setNome(professorNovo.getNome()); 
		}
		if(professorNovo.getCurso() != null) {
			professorAntigo.setCurso(professorNovo.getCurso()); 
		}
		if(professorNovo.getCpf() != null) {
			professorAntigo.setCpf(professorNovo.getCpf()); 
		}
		
		return professorRepository.save(professorAntigo);
		
	}
	
	public void deleterProfessor(Long id) {
		professorRepository.deleteById(id);
	}
}
