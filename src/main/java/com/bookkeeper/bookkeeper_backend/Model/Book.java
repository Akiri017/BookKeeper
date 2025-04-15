package com.bookkeeper.bookkeeper_backend.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    private LocalDate dateStarted;
    private LocalDate dateFinished;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    private int rating;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private String favoriteQuotes;
    private String tags;

    private String coverImageUrl;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public LocalDate getDateStarted() {
        return dateStarted;
    }
    
    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }
    
    public LocalDate getDateFinished() {
        return dateFinished;
    }
    
    public void setDateFinished(LocalDate dateFinished) {
        this.dateFinished = dateFinished;
    }
    
    public ReadingStatus getStatus() {
        return status;
    }
    
    public void setStatus(ReadingStatus status) {
        this.status = status;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }    

    public String getFavoriteQuotes() { 
        return favoriteQuotes; 
    }

    public void setFavoriteQuotes(String favoriteQuotes) { 
        this.favoriteQuotes = favoriteQuotes; 
    }

    public String getTags() { 
        return tags; 
    }

    public void setTags(String tags) { 
        this.tags = tags; 
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }
    
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
