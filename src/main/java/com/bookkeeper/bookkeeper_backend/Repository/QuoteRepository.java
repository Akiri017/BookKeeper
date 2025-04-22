package com.bookkeeper.bookkeeper_backend.Repository;

import com.bookkeeper.bookkeeper_backend.Model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    
}
