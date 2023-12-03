package com.pjt.gingging.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.gingging.model.dao.PosterDao;
import com.pjt.gingging.model.dto.Poster;

@Service
public class PosterServiceImpl implements PosterService {

	@Autowired
	private PosterDao pd;

	@Override
	public List<Poster> getPoster() {
		return pd.getPoster();
	}

	@Override
	public Poster getPosterDetail(int poster_id) {
		return pd.getPosterDetail(poster_id);
	}
	
	@Override
	@Transactional
	public int makePoster(Poster poster) {
		pd.makePoster(poster);

		int result = poster.getPoster_id();

		return result;
	}


	@Override
	public int deletePoster(int poster_id) {
		return pd.deletePoster(poster_id);
	}

	@Override
	public List<Poster> getPosterByUser(int user_id) {
		return pd.getPosterByUser(user_id);
	}

}
