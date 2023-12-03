package com.pjt.gingging.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.gingging.model.dao.UserDao;
import com.pjt.gingging.model.dto.User;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao ud;

	@Override
	public List<User> selectAll() {
		return ud.selectAll();
	}

	@Override
	public int signup(User user) {
		return ud.signup(user);
	}


	@Override
	public User login(String id, String password) {
		User tmp = ud.login(id, password);
		
		if(tmp != null && tmp.getPassword().equals(password)) {
			return tmp;
		}
		
		return null;
	}
	
	// 중복 검사
	@Override
	public boolean isDuplicate(String id) {
		return ud.isDuplicate(id) != 0;
	}

}
