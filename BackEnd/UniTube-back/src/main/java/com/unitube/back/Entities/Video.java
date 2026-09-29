package com.unitube.back.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
	
	@Id @GeneratedValue
	private Long id;
	
	private String titulo;
	
	private String curso;
	
	private String professor;
	
	private Integer data;
	
	private Integer duracao;

}
