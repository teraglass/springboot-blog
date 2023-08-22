package me.tera.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tera.blog.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
	private String title;
	private String content;

	public Article toEntity(){ // 생성자로 객체 생성
		return Article.builder()
			.title(title)
			.content(content)
			.build();
	}

}
