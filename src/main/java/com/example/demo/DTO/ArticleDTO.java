package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class ArticleDTO {

    private int id;
    @NotBlank(message = "Author should not be null or empty")
    private String author;

    @NotBlank(message = "Title should not be null or empty")
    @Size(max = 100, message = "Title should not exceed 100 characters")
    private String title;

    @NotBlank(message = "Content should not be null or empty")
    private String content;

    private Date datePublished;

    public ArticleDTO() {
    }

    public ArticleDTO(int id, String author, String title, String content, Date datePublished) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.datePublished = datePublished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
}
