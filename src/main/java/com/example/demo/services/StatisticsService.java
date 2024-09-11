package com.example.demo.services;

import com.example.demo.repositories.ArticlesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

@Service
@Transactional(readOnly = true)
public class StatisticsService {

    private final ArticlesRepository articlesRepository;

    private static final int DAYS_FOR_STATISTICS = 7;

    public StatisticsService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    public TreeMap<String, Long> showCountOfArticlesDaily(){

        TreeMap<String, Long> mapOfStatistics = new TreeMap<>();
        Date currentDate;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        for(int day = 0; day < DAYS_FOR_STATISTICS; day++){

            currentDate = Date.from(ZonedDateTime.now().minusDays(day).toInstant());

            mapOfStatistics.put(formatter.format(currentDate), articlesRepository.countAllByDatePublished(currentDate));

        }

        return mapOfStatistics;
    }
}
