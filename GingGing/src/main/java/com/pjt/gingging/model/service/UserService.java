package com.pjt.gingging.model.service;

import java.util.List;

import com.pjt.gingging.model.dto.User;

public interface UserService {

	List<User> selectAll();

	int signup(User user);

	User login(String id, String password);

	boolean isDuplicate(String id);


	

}
