package com.unitube.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitube.back.Entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
