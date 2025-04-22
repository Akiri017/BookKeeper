package com.bookkeeper.bookkeeper_backend;

import com.bookkeeper.bookkeeper_backend.Model.Quote;
import com.bookkeeper.bookkeeper_backend.Model.ReadingStatus;
import com.bookkeeper.bookkeeper_backend.Model.Tag;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookUpdateDTO {
    @Size(max = 255)
    private String title;
    
    @Size(max = 255)
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStarted;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinished;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;
    
    @Min(1)
    @Max(5)
    private Integer rating;
    
    @Size(max = 1000)
    private String summary;
    
    @Size(max = 255)
    private String coverImageUrl;

    private List<Quote> addQuotes;
    private List<Long> removeQuoteIds;
    private Set<Tag> addTags;
    private Set<Long> removeTagIds;
    
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
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }    

    public String getCoverImageUrl() {
        return coverImageUrl;
    }
    
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<Quote> getAddQuotes() {
        return addQuotes;
    }
    public void setAddQuotes(List<Quote> addQuotes) {
        this.addQuotes = addQuotes;
    }

    public List<Long> getRemoveQuoteIds() {
        return removeQuoteIds;
    }
    public void setRemoveQuoteIds(List<Long> removeQuoteIds) {
        this.removeQuoteIds = removeQuoteIds;
    }

    public Set<Tag> getAddTags() {
        return addTags;
    }
    public void setAddTags(Set<Tag> addTags) {
        this.addTags = addTags;
    }

    public Set<Long> getRemoveTagIds() {
        return removeTagIds;
    }
    public void setRemoveTagIds(Set<Long> removeTagIds) {
        this.removeTagIds = removeTagIds;
    }
}
