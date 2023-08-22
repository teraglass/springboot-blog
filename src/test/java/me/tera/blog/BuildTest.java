package me.tera.blog;

import me.tera.blog.domain.Article;
import me.tera.blog.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BuildTest {

	@Autowired
//	Article article;

	@Test
	public void BuildTest() {
		Article article1 = Article.builder()
			.content("test")
			.title("title")
			.build();

	}

}
