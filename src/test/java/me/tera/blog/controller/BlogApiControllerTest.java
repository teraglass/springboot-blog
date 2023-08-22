package me.tera.blog.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.tera.blog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

}