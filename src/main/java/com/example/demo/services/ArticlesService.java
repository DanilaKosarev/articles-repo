package com.example.demo.services;

import com.example.demo.models.Article;
import com.example.demo.repositories.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticlesService {

    private final ArticlesRepository articlesRepository;

    @Autowired
    public ArticlesService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    public List<Article> findAll(int page, int itemsPerPage){
        return articlesRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }

    public List<Article> findAll(){
        return articlesRepository.findAll();
    }

    @Transactional
    public void createArticle(Article articleToSave){

        articleToSave.setId(0);
        articleToSave.setDatePublished(new Date());

        articlesRepository.save(articleToSave);
    }
}
