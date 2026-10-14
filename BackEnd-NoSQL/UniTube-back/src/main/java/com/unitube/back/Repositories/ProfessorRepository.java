package com.unitube.back.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.unitube.back.Entities.Professor;

public interface ProfessorRepository extends MongoRepository<Professor, String> {

}
