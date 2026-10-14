package com.unitube.back.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "professores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
	
	@Id
	private String id;
	
	private String cpf;
	
	private String nome;
	
	private String curso;
			
}
