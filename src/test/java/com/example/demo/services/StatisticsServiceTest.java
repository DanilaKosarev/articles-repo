package com.example.demo.services;

import com.example.demo.repositories.ArticlesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;

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
        assertThat(underTest.showCountOfArticlesDaily()).isNotNull();
    }
}
