package com.pjt.gingging.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.gingging.model.dto.Jogging;
import com.pjt.gingging.model.dto.Poster;
import com.pjt.gingging.model.service.JoggingService;
import com.pjt.gingging.model.service.PosterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Api(description = "포스터 만들기")
@RequestMapping("/api")
public class PosterController {

	@Autowired
	private PosterService ps;

	@Autowired
	private JoggingService js;

	@Autowired
	private ResourceLoader resLoader;

	@Value("${upload.path}")
	private String UPLOAD_PATH;

	@GetMapping("/poster")
	@ApiOperation(value = "포스터 목록")
	private ResponseEntity<?> getPoster() {

		List<Poster> posters = ps.getPoster();

		if (posters == null || posters.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Poster>>(posters, HttpStatus.OK);

	}
	
	@GetMapping("/poster/{poster_id}")
	@ApiOperation(value = "포스터 상세")
	private ResponseEntity<?> getPosterDetail(@PathVariable int poster_id) {

		Poster poster = ps.getPosterDetail(poster_id);

		if (poster == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<Poster>(poster, HttpStatus.OK);

	}

	@PostMapping("/poster")
	@ApiOperation(value = "포스터 등록")
	private ResponseEntity<?> makePoster(@ModelAttribute Poster poster, MultipartFile file, int jog_id) {

		if (file != null && !file.isEmpty()) {
			poster.setUser_photo(System.currentTimeMillis() + "_" + file.getOriginalFilename()); // 이름 중복 방지 위해 파일 이름 앞에 시간 추가
			poster.setUser_org_photo(file.getOriginalFilename());

			try {
				File uploadDirectory = new File(UPLOAD_PATH);
				if (!uploadDirectory.exists()) {
					uploadDirectory.mkdirs(); // 디렉토리가 존재하지 않으면 생성
				}

				file.transferTo(new File(uploadDirectory + "/" + poster.getUser_org_photo()));

			} catch (Exception e) {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
				
			}

		}

		poster.setJog_id(jog_id);
		Jogging jogging = js.getJoggingDetail(jog_id);
		poster.setUser_id(jogging.getUser_id()); // 조깅에서 사용자 아이디 얻어와야 함

		try {
			int result = ps.makePoster(poster);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@DeleteMapping("/poster/{poster_id}")
	@ApiOperation(value = "포스터 삭제")
	private ResponseEntity<?> deletePoster(@PathVariable int poster_id) {
		int result = ps.deletePoster(poster_id);

		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);

	}

	@GetMapping("/poster/user/{user_id}")
	@ApiOperation(value = "회원별 포스터 목록")
	private ResponseEntity<?> getPosterByUser(@PathVariable int user_id) {
		List<Poster> posters = ps.getPosterByUser(user_id);

		if (posters == null || posters.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Poster>>(posters, HttpStatus.OK);

	}

}
