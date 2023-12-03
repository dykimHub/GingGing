package com.pjt.gingging.model.dao;

import java.util.List;

import com.pjt.gingging.model.dto.Jogging;

public interface JoggingDao {

	Jogging getJoggingDetail(int jog_id);

	int deleteJogging(int jog_id);

	List<Jogging> getJoggingByUser(int user_id);

	int registJogging(Jogging jog);

}
