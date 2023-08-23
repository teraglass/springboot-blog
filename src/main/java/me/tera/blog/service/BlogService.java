package me.tera.blog.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.tera.blog.domain.Article;
import me.tera.blog.dto.AddArticleRequest;
import me.tera.blog.dto.UpdateArticleRequest;
import me.tera.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {
	private final BlogRepository blogRepository;

	// 블로그 글 추가 메서드
	public Article save(AddArticleRequest request){
		return blogRepository.save(request.toEntity());
	}

	public List<Article> findAll(){
		return blogRepository.findAll();
	}
	public Article findById(Long id){
		return blogRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + id));
	}

	public void deleteById(long id){
		blogRepository.deleteById(id);
	}

	@Transactional
	public Article update(long id, UpdateArticleRequest request){
		Article article = blogRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + id));

		article.update(request.getTitle(), request.getContent());

		return article;
	}
}
