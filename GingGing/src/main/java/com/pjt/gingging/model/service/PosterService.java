package com.pjt.gingging.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pjt.gingging.model.dto.Poster;

public interface PosterService {

	int makePoster(Poster poster);

	int deletePoster(int poster_id);

	List<Poster> getPosterByUser(int user_id);

	List<Poster> getPoster();

	Poster getPosterDetail(int poster_id);

}
