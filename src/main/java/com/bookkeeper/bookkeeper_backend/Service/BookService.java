package com.bookkeeper.bookkeeper_backend.Service;

import com.bookkeeper.bookkeeper_backend.Model.Book;
import com.bookkeeper.bookkeeper_backend.Repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public Book updateBook(Long id, Book updatedBookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(updatedBookDetails.getTitle());
        existingBook.setAuthor(updatedBookDetails.getAuthor());
        existingBook.setDateStarted(updatedBookDetails.getDateStarted());
        existingBook.setDateFinished(updatedBookDetails.getDateFinished());
        existingBook.setStatus(updatedBookDetails.getStatus());
        existingBook.setRating(updatedBookDetails.getRating());
        existingBook.setSummary(updatedBookDetails.getSummary());
        existingBook.setFavoriteQuotes(updatedBookDetails.getFavoriteQuotes());
        existingBook.setTags(updatedBookDetails.getTags());
        existingBook.setCoverImageUrl(updatedBookDetails.getCoverImageUrl());

        return bookRepository.save(existingBook);
    }

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
