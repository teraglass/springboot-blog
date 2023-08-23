package me.tera.blog.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.tera.blog.domain.Article;
import me.tera.blog.dto.AddArticleRequest;
import me.tera.blog.dto.ArticleResponse;
import me.tera.blog.dto.UpdateArticleRequest;
import me.tera.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BlogApiController {

	private final BlogService blogService;

	@PostMapping("/articles")
	public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
		Article savedArticle = blogService.save(request);
		// 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(savedArticle);
	}

	@GetMapping("/articles")
	public ResponseEntity<List<ArticleResponse>> findAllArticles(){
		List<ArticleResponse> articles = blogService.findAll()
			.stream()
			.map(ArticleResponse::new)
			.toList();
		return ResponseEntity.ok()
			.body(articles);
	}
	@GetMapping("/articles/{id}")
	public ResponseEntity<ArticleResponse> findArticleById(@PathVariable long id){
		Article article = blogService.findById(id);

		return ResponseEntity.ok()
			.body(new ArticleResponse(article));

	}

	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable long id){
		blogService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable long id,
		@RequestBody UpdateArticleRequest request){
		Article updatedArticle = blogService.update(id, request);

		return ResponseEntity.ok().body(updatedArticle);
	}

}
