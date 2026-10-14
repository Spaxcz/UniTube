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

import com.unitube.back.Entities.Video;
import com.unitube.back.Services.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@GetMapping
	private ResponseEntity<List<Video>> listarVideos(){
		return ResponseEntity.ok(videoService.listarVideos());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Video> retornarVideo(@PathVariable String id){
		Optional<Video> videoPorId = videoService.retornarVideo(id);
		
		if(videoPorId.isPresent()) {
			 return ResponseEntity.ok(videoPorId.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	private ResponseEntity<Video> criarVideo(@RequestBody Video video){
		Video videoNovo = videoService.criarVideo(video);
		return ResponseEntity.status(201).body(videoNovo);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Video> atualizarVideo(@PathVariable String id, @RequestBody Video video){
		Video videoAtualizado = videoService.atualizarVideo(id, video);
		return ResponseEntity.status(200).body(videoAtualizado);

	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deletarVideo(@PathVariable String id){
		videoService.deletarVideo(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/busca-titulo/{titulo}")
	private ResponseEntity<List<Video>> listarVideoPorTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(videoService.listarVideoPorTitulo(titulo));
	}
	
	@GetMapping("/busca-data/{dataInicial}/{dataFinal}")
	private ResponseEntity<List<Video>> buscaPorData(@PathVariable Integer dataInicial,@PathVariable Integer dataFinal){
		return ResponseEntity.ok(videoService.buscaPorData(dataInicial, dataFinal));
	}

}
