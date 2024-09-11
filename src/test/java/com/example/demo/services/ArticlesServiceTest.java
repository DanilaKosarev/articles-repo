package com.example.demo.services;

import com.example.demo.models.Article;
import com.example.demo.repositories.ArticlesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.verify;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticlesServiceTest {

    private ArticlesService underTest;

    @Mock
    private ArticlesRepository articlesRepository;

    @BeforeEach
    void setUp(){underTest = new ArticlesService(articlesRepository);}

    @Test
    void shouldCallFindAllWithZeroArgs(){
        //when
        underTest.findAll();

        //then
        verify(articlesRepository).findAll();
    }

    @Test
    void shouldCreateNewArticle(){
        //given
        Article testArticle = mock(Article.class);

        //when
        underTest.createArticle(testArticle);

        //then
        ArgumentCaptor<Article> articleArgumentCaptor = ArgumentCaptor.forClass(Article.class);

        verify(articlesRepository).save(articleArgumentCaptor.capture());

        Article capturedArticle = articleArgumentCaptor.getValue();
        assertThat(capturedArticle).isEqualTo(testArticle);
    }

}
