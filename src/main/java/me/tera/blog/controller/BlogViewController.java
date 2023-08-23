package me.tera.blog.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.tera.blog.domain.Article;
import me.tera.blog.dto.ArticleListViewResponse;
import me.tera.blog.dto.ArticleViewResponse;
import me.tera.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/articles")
public class BlogViewController {

	private final BlogService blogService;

	@GetMapping()
	public String getArticles(Model model){
		List<ArticleListViewResponse> articles = blogService.findAll().stream()
			.map(ArticleListViewResponse::new)
			.toList();
		model.addAttribute("articles", articles);
		return "articleList";
	}

	@GetMapping("/{id}")
	public String getArticle(@PathVariable Long id, Model model){
		Article article = blogService.findById(id);
		model.addAttribute("article", new ArticleViewResponse(article));
		return "article";
	}
}
