package com.findwise.homework.controller;

import com.findwise.homework.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchController {

	private final SearchService service;

	public SearchController(SearchService service) {
		this.service = service;
	}

	@RequestMapping("/search/{term}")
	public @ResponseBody List<String> search(@PathVariable(value = "term") String term) {
		return service.tfidf(term);
	}

}