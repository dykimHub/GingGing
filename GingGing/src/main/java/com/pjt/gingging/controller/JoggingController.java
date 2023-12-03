package com.pjt.gingging.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.pjt.gingging.model.dto.Jogging;
import com.pjt.gingging.model.dto.User;
import com.pjt.gingging.model.service.JoggingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Api(description = "조깅 경로 고르기")
@RequestMapping(value = "/api")
//, produces = "application/text; charset=utf8"
public class JoggingController {

	@Autowired
	private JoggingService js;

	final String NAVER_API_ID = "KNjcJNmLeDS207BrA2RD";
	final String NAVER_API_SECRET = "gmFDhRPpzT";
	final String KAKAO_API_KEY = "KakaoAK 014d011708e67227c1e30343b865fd43";

	// 등록되고 jog_id 반환
	@PostMapping("/jogging")
	@ApiOperation(value = "조깅 경로 등록")
	public ResponseEntity<?> registJogging(@RequestBody Jogging jog) {
		try {
			int result = js.registJogging(jog);
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/jogging/search/{word}")
	@ApiOperation(value = "공원, 하천 검색 결과")
	private String searchWord(@PathVariable String word) {
		URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com/").path("v1/search/local.json")
				.queryParam("query", word).queryParam("display", 20) // 최대 표시
				.queryParam("start", 1) // 검색 시작 위치
				.queryParam("sort", "random") // 정확도순으로 내림차순 정렬
				.encode(StandardCharsets.UTF_8).build().toUri();

		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<Void> req = RequestEntity.get(uri).header("X-Naver-Client-Id", NAVER_API_ID)
				.header("X-Naver-Client-Secret", NAVER_API_SECRET).build();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}

	@GetMapping("/jogging/change/{x}/{y}")
	@ApiOperation(value = "지역 검색 결과")
	private String searchLocation(@PathVariable String x, @PathVariable String y) {

		URI uri = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/")
				.path("v2/local/geo/coord2regioncode.json").queryParam("x", x) // 경도
				.queryParam("y", y) // 위도
				.encode(StandardCharsets.UTF_8).build().toUri();

		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<Void> req = RequestEntity.get(uri).header("Authorization", KAKAO_API_KEY).build();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();

	}

	@DeleteMapping("/jogging/{jog_id}")
	@ApiOperation(value = "조깅 경로 삭제")
	private ResponseEntity<?> deleteJogging(@PathVariable int jog_id) {
		int result = js.deleteJogging(jog_id);

		if (result == 0)
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);

	}

	@GetMapping("/jogging/user/{user_id}")
	@ApiOperation(value = "회원별 조깅 경로")
	private ResponseEntity<?> getJoggingByUser(@PathVariable int user_id) {
		List<Jogging> jogs = js.getJoggingByUser(user_id);

		if (jogs.size() == 0 || jogs == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Jogging>>(jogs, HttpStatus.OK);

	}

	// 포스터 등록할 때 필요함
	// 조깅 장소 찾을 때 필요함
	@GetMapping("/jogging/{jog_id}")
	@ApiOperation(value = "조깅 상세")
	private ResponseEntity<?> getJoggingDetail(@PathVariable int jog_id) {
		Jogging jog = js.getJoggingDetail(jog_id);

		if (jog == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<Jogging>(jog, HttpStatus.OK);

	}
	
}
