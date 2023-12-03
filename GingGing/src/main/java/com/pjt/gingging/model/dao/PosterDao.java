package com.pjt.gingging.model.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pjt.gingging.model.dto.Poster;

public interface PosterDao {

	List<Poster> getPoster();
	
	Poster getPosterDetail(int poster_id);
	
	int makePoster(Poster poster);

	int deletePoster(int poster_id);

	List<Poster> getPosterByUser(int user_id);



}
