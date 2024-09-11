package com.example.demo.controllers;

import com.example.demo.DTO.ArticleDTO;
import com.example.demo.services.ArticlesService;
import com.example.demo.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    private final ArticlesService articlesService;
    private final Mapper mapper;

    @Autowired
    public ArticlesController(ArticlesService articlesService, Mapper mapper) {
        this.articlesService = articlesService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ArticleDTO> findAllArticles(@RequestParam(name = "page", required = false) Integer page,
                                            @RequestParam(name = "articles_per_page", required = false) Integer articlesPerPage){
        if(page != null && articlesPerPage != null)
            return articlesService.findAll(page, articlesPerPage).stream().map(mapper::convertArticleToArticleDto).collect(Collectors.toList());
        else
            return articlesService.findAll().stream().map(mapper::convertArticleToArticleDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticle(@RequestBody @Valid ArticleDTO articleDTO){
        articlesService.createArticle(mapper.convertArticleDtoToArticle(articleDTO));
    }
}
