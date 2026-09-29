package com.unitube.back.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitube.back.Entities.Professor;
import com.unitube.back.Services.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping
	private ResponseEntity<List<Professor>> listarProfessores(){
		return ResponseEntity.ok(professorService.listarProfessores());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Professor> procurarProfessor(@PathVariable Long id){
		Optional<Professor> professorPorId = professorService.procurarProfessor(id);
		
		if(professorPorId.isPresent()) {
			 return ResponseEntity.ok(professorPorId.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	private ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor){
		Professor professorNovo = professorService.criarProfessor(professor);
		return ResponseEntity.status(201).body(professorNovo);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor){
		Professor professorAtualizado = professorService.atualizarProfessor(id, professor);
		return ResponseEntity.status(200).body(professorAtualizado);

	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deletarProfessor(@PathVariable Long id){
		professorService.deleterProfessor(id);
		return ResponseEntity.noContent().build();
	}

}
