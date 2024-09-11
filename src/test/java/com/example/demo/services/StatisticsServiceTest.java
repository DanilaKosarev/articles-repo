package com.example.demo.services;

import com.example.demo.repositories.ArticlesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

    private StatisticsService underTest;
    @Mock
    private ArticlesRepository articlesRepository;

    @BeforeEach
    void setUp(){underTest = new StatisticsService(articlesRepository);}

    @Test
    void shouldCallArticlesRepositoryMethods(){
        //when
        underTest.showCountOfArticlesDaily();

        //then
        verify(articlesRepository, times(7)).countAllByDatePublished(any());

        assertThat(underTest.showCountOfArticlesDaily()).isNotNull();
        assertThat(underTest.showCountOfArticlesDaily().size()).isEqualTo(7);
    }
}
