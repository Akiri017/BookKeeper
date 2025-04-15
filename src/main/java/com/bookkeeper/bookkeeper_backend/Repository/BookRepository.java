package com.bookkeeper.bookkeeper_backend.Repository;

import com.bookkeeper.bookkeeper_backend.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}