package com.unitube.back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitube.back.Entities.Video;
import com.unitube.back.Repositories.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	private VideoRepository videoRepository;
	
	public List<Video> listarVideos() {
		return videoRepository.findAll();
	}
	
	public Optional<Video> retornarVideo(Long id) {
		return videoRepository.findById(id);
	}
	
	public Video criarVideo(Video video) {
		videoRepository.save(video);
		return video;
	}
	
	public Video atualizarVideo(Long id, Video videoNovo) {
		Video videoAntigo = videoRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Video não encontrado."));
		
		if(videoNovo.getTitulo() != null) {
			videoAntigo.setTitulo(videoNovo.getTitulo()); 
		}
		if(videoNovo.getCurso() != null) {
			videoAntigo.setCurso(videoNovo.getCurso()); 
		}
		if(videoNovo.getProfessor() != null) {
			videoAntigo.setProfessor(videoNovo.getProfessor()); 
		}
		if(videoNovo.getData() != null) {
			videoAntigo.setData(videoNovo.getData()); 
		}
		if(videoNovo.getDuracao() != null) {
			videoAntigo.setDuracao(videoNovo.getDuracao()); 
		}
		
		return videoRepository.save(videoAntigo);
		
	}
	
	public void deleterVideo(Long id) {
		videoRepository.deleteById(id);
	}
}
