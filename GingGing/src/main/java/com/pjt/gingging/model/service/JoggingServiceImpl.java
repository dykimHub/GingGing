package com.pjt.gingging.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt.gingging.model.dao.JoggingDao;
import com.pjt.gingging.model.dto.Jogging;

@Service
public class JoggingServiceImpl implements JoggingService {

	@Autowired
	private JoggingDao jd;

	@Override
	public int deleteJogging(int jog_id) {
		return jd.deleteJogging(jog_id);
	}

	@Override
	public List<Jogging> getJoggingByUser(int user_id) {
		return jd.getJoggingByUser(user_id);
	}

	@Override
	@Transactional
	public int registJogging(Jogging jog) {
		jd.registJogging(jog);
		
		int result = jog.getJog_id();
		
		return result;
	}

	@Override
	public Jogging getJoggingDetail(int jog_id) {
		return jd.getJoggingDetail(jog_id);
	}

}
