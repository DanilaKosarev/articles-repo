package com.example.demo.util;

import com.example.demo.DTO.ArticleDTO;
import com.example.demo.DTO.UserAccountDTO;
import com.example.demo.models.Article;
import com.example.demo.models.UserAccount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper modelMapper;

    @Autowired
    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ArticleDTO convertArticleToArticleDto(Article article){
        return modelMapper.map(article, ArticleDTO.class);
    }

    public Article convertArticleDtoToArticle(ArticleDTO articleDTO){
        return modelMapper.map(articleDTO, Article.class);
    }

    public UserAccount convertUserAccountDtoToUserAccount(UserAccountDTO userAccountDTO){
        return modelMapper.map(userAccountDTO, UserAccount.class);
    }
}
