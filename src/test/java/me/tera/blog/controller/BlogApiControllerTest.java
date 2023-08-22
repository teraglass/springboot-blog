package me.tera.blog.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.tera.blog.dto.AddArticleRequest;
import me.tera.blog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* 직렬화 : 자바obj -> JSON
* 역직렬화 : JSON -> 자바 obj
*/
@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

	@Autowired
	protected MockMvc mockMvc;


	@Autowired
	protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스

	@Autowired
	private WebApplicationContext context;

	@Autowired
	BlogRepository blogRepository;

	@BeforeEach // 테스트 실행 전
	public void setMockMvc(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		blogRepository.deleteAll();
	}
	@DisplayName("addArticle: 블로그 글 추가에 성공한다.")
	@Test
	public void addArticle() throws Exception{

		// given
		final String url = "/api/articles";
		final String title = "title";
		final String content = "content";
		final AddArticleRequest userRequest = new AddArticleRequest(title, content);

		// 객체 JSON으로 직렬화
		final String requestBody = objectMapper.writeValueAsString(userRequest);

		// when
		// 설정한 내용으로 요청 전송
		ResultActions result = mockMvc.perform(post(url)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(requestBody));

		// then
		result.andExpect(status().isCreate())
	}
}