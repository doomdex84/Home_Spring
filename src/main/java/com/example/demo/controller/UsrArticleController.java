package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {

	int lastArticleId;
	List<Article> articles;

	public UsrArticleController() {
		articles = new ArrayList<>();
		lastArticleId = 0;
		
		makeTestDate();
	}
	
	private void makeTestDate() {
		for(int i = 0; i<=10; i++) {
			String title = "제목"+1;
			String body = "내용"+1;
			
			writeArticle(title,body);
		}
	}

	private Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);

		lastArticleId++;
		
		return article;
	}

	private Article getArticleById(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) { 

		Article article = getArticleById(id);
		
		if(article == null) {
			return id + "번 글이 없음";
		}
		articles.remove(article);

		return article;
	}
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) { 

		Article article = writeArticle(title, body);

		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articles;
	}
}