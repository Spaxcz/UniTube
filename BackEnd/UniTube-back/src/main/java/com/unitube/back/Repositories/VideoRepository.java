package com.unitube.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitube.back.Entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
