package com.bookkeeper.bookkeeper_backend.Repository;

import com.bookkeeper.bookkeeper_backend.Model.Book;
import com.bookkeeper.bookkeeper_backend.Model.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByStatus(ReadingStatus status);
    List<Book> findByRating(Integer rating);
    List<Book> findByRatingGreaterThanEqual(Integer rating);
    List<Book> findByRatingLessThanEqual(Integer rating);
    
    @Query("SELECT b FROM Book b JOIN b.tags t WHERE t.name = :tagName")
    List<Book> findByTagName(@Param("tagName") String tagName);
    
    @Query("SELECT b FROM Book b WHERE b.dateStarted >= :startDate")
    List<Book> findByDateStartedAfter(@Param("startDate") String startDate);
    
    @Query("SELECT b FROM Book b WHERE b.dateFinished <= :endDate")
    List<Book> findByDateFinishedBefore(@Param("endDate") String endDate);
}