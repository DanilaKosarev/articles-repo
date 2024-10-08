package com.example.demo.repositories;

import com.example.demo.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Integer> {

    long countAllByDatePublished(Date date);

}
