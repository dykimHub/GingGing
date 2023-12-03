package com.pjt.gingging.model.dao;

import java.util.List;

import com.pjt.gingging.model.dto.User;


public interface UserDao {

	//모든 사용자 리스트 반환
	List<User> selectAll();
	
	//회원가입
	int signup(User user);
	
	//사용자 로그인
	User login(String id, String password);

	//사용자 상세보기
	User detail(int user_id);

	int isDuplicate(String id);
}
