package com.unitube.back.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.unitube.back.Entities.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
	
	List<Video> findByTitulo(String titulo);
	
	@Query("{ 'data': {'$gte': ?0, '$lte': ?1 } }")
	List<Video> buscaPorData(Integer dataInicial, Integer dataFinal);
	

}
