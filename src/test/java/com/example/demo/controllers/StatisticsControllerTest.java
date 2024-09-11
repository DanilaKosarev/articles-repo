package com.example.demo.controllers;

import com.example.demo.security.JWTUtil;
import com.example.demo.services.StatisticsService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StatisticsController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class StatisticsControllerTest {

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private Mapper mapper;

    @MockBean
    private JWTUtil jwtUtil;

    @MockBean
    UserAccountDetailsService userAccountDetailsService;

    @InjectMocks
    private StatisticsController underTest;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnStatistics() throws Exception {
        //when
        ResultActions result = mockMvc.perform(get("/api/statistics"));

        //then
        result.andExpect(status().isOk());

        verify(statisticsService, times(1)).showCountOfArticlesDaily();
    }

}
