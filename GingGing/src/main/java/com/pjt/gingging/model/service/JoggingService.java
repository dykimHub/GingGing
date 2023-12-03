package com.pjt.gingging.model.service;

import java.util.List;

import com.pjt.gingging.model.dto.Jogging;

public interface JoggingService {

	int deleteJogging(int jog_id);

	List<Jogging> getJoggingByUser(int user_id);

	int registJogging(Jogging jog);

	Jogging getJoggingDetail(int jog_id);

}
