package com.pjt.gingging.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt.gingging.model.dto.User;
import com.pjt.gingging.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(description="회원 정보")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService us;

	@ApiOperation(value = "전체 회원 목록")
	@GetMapping("/user")
	private ResponseEntity<?> selectAll() {
		List<User> users = us.selectAll();

		if (users.size() == 0 || users == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입")
	@PostMapping("/user/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		// 중복검사
		String id = user.getId();
		if (us.isDuplicate(id))
			return new ResponseEntity<String>("중복된 아이디 입니다.", HttpStatus.BAD_REQUEST);
		
		int result = us.signup(user);

		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	@ApiOperation(value = "로그인")
	@PostMapping("/user/login")
	public ResponseEntity<?> login(String id, String password, @ApiIgnore HttpSession session) {
		
		User tmp = us.login(id, password);
		
		if (tmp == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<User>(tmp, HttpStatus.OK);
	}

}
