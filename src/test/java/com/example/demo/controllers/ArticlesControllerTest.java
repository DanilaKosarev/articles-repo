package com.example.demo.controllers;

import com.example.demo.models.Article;
import com.example.demo.security.JWTUtil;
import com.example.demo.services.ArticlesService;
import com.example.demo.services.UserAccountDetailsService;
import com.example.demo.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ArticlesController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArticlesControllerTest {
    @MockBean
    private ArticlesService articlesService;

    @MockBean
    private Mapper mapper;

    @MockBean
    private JWTUtil jwtUtil;

    @MockBean
    UserAccountDetailsService userAccountDetailsService;

    @InjectMocks
    private ArticlesController underTest;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSuccessfullyCreateNewArticle() throws Exception{
        //given
        Article testArticle = new Article("TestAuthor", "TestTitle", "TestContent");
        String articleJson = objectMapper.writeValueAsString(testArticle);

        given(mapper.convertArticleDtoToArticle(any())).willReturn(testArticle);

        //when
        ResultActions result = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(articleJson));
        //then
        result.andExpect(status().isCreated());

        verify(articlesService, times(1)).createArticle(testArticle);
    }

    @Test
    void shouldReturnBadRequestForInvalidTitleLength() throws Exception{
        //given
        Article testArticle = new Article("TestAuthor", "111111111111111111" +
                                                                    "1111111111111111111111111111" +
                                                                    "111111111111111111111111111111" +
                                                                    "1111111111111111111111111", "TestContent");
        String articleJson = objectMapper.writeValueAsString(testArticle);

        given(mapper.convertArticleDtoToArticle(any())).willReturn(testArticle);

        //when
        ResultActions result = mockMvc.perform(post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(articleJson));
        //then
        result.andExpect(status().isBadRequest());

        verifyNoInteractions(articlesService);
    }

    @Test
    void shouldCallServiceMethodWithPagination() throws Exception{
        //given
        List<Article> resultList = mock(List.class);

        int page = 1;
        int itemsPerPage = 2;

        given(articlesService.findAll(page,itemsPerPage)).willReturn(resultList);

        //when
        ResultActions result = mockMvc.perform(get("/api/articles")
                .param("page", "1")
                .param("articles_per_page", "2"));

        //then
        verify(articlesService, times(1)).findAll(page,itemsPerPage);
        result.andExpect(status().isOk());

    }

    @Test
    void shouldCallServiceMethodWithoutPagination() throws Exception{
        //given
        List<Article> resultList = mock(List.class);

        given(articlesService.findAll()).willReturn(resultList);

        //when
        ResultActions result = mockMvc.perform(get("/api/articles"));


        //then
        verify(articlesService, times(1)).findAll();
        result.andExpect(status().isOk());

    }

}
