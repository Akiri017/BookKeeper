package com.bookkeeper.bookkeeper_backend.Service;

import com.bookkeeper.bookkeeper_backend.Model.Book;
import com.bookkeeper.bookkeeper_backend.Model.Quote;
import com.bookkeeper.bookkeeper_backend.Model.Tag;
import com.bookkeeper.bookkeeper_backend.Model.ReadingStatus;
import com.bookkeeper.bookkeeper_backend.Repository.BookRepository;
import com.bookkeeper.bookkeeper_backend.Repository.QuoteRepository;
import com.bookkeeper.bookkeeper_backend.Repository.TagRepository;
import com.bookkeeper.bookkeeper_backend.BookUpdateDTO;
import com.bookkeeper.bookkeeper_backend.Exception.BookNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    
    private final BookRepository bookRepository;
    private final QuoteRepository quoteRepository;
    private final TagRepository tagRepository;

    @Autowired
    public BookService(BookRepository bookRepository, 
                      QuoteRepository quoteRepository,
                      TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.quoteRepository = quoteRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Book addBook(Book book) {
        logger.info("Adding new book: {}", book.getTitle());
        if (book.getQuotes() != null) {
            for (Quote quote : book.getQuotes()) {
                quote.setBook(book);
            }
        }
        
        if (book.getTags() != null) {
            Set<Tag> processedTags = new HashSet<>();
            for (Tag tag : book.getTags()) {
                Optional<Tag> existingTag = tagRepository.findByName(tag.getName());
                if (existingTag.isPresent()) {
                    processedTags.add(existingTag.get());
                } else {
                    tag.getBooks().add(book);
                    processedTags.add(tag);
                }
            }
            book.setTags(processedTags);
        }

        Book savedBook = bookRepository.save(book);
        logger.info("Book added successfully with ID: {}", savedBook.getId());
        return savedBook;
    }

    @Transactional
    public Book updateBook(Long id, BookUpdateDTO updatedBookDetails) {
        logger.info("Updating book with ID: {}", id);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Book not found with ID: {}", id);
                    return new BookNotFoundException(id);
                });
    
        updateBasicBookDetails(existingBook, updatedBookDetails);
        updateQuotes(existingBook, updatedBookDetails);
        updateTags(existingBook, updatedBookDetails);
    
        Book updatedBook = bookRepository.save(existingBook);
        logger.info("Book updated successfully: {}", updatedBook.getTitle());
        return updatedBook;
    }

    private void updateBasicBookDetails(Book book, BookUpdateDTO update) {
        if (update.getTitle() != null) {
            book.setTitle(update.getTitle());
        }
        if (update.getAuthor() != null) {
            book.setAuthor(update.getAuthor());
        }
        if (update.getDateStarted() != null) {
            book.setDateStarted(update.getDateStarted());
        }
        if (update.getDateFinished() != null) {
            book.setDateFinished(update.getDateFinished());
        }
        if (update.getStatus() != null) {
            book.setStatus(update.getStatus());
        }
        if (update.getRating() != null) {
            book.setRating(update.getRating());
        }
        if (update.getSummary() != null) {
            book.setSummary(update.getSummary());
        }
        if (update.getCoverImageUrl() != null) {
            book.setCoverImageUrl(update.getCoverImageUrl());
        }
    }

    private void updateQuotes(Book book, BookUpdateDTO update) {
        // Add new quotes
        if (update.getAddQuotes() != null && !update.getAddQuotes().isEmpty()) {
            for (Quote quote : update.getAddQuotes()) {
                quote.setBook(book);
                book.addQuote(quote);
            }
        }

        // Remove quotes by ID
        if (update.getRemoveQuoteIds() != null && !update.getRemoveQuoteIds().isEmpty()) {
            List<Quote> quotesToRemove = book.getQuotes().stream()
                .filter(quote -> update.getRemoveQuoteIds().contains(quote.getId()))
                .collect(Collectors.toList());
            
            quotesToRemove.forEach(quote -> {
                book.removeQuote(quote);
                quoteRepository.delete(quote);
            });
        }
    }

    private void updateTags(Book book, BookUpdateDTO update) {
        // Add new tags
        if (update.getAddTags() != null && !update.getAddTags().isEmpty()) {
            for (Tag tag : update.getAddTags()) {
                // Check if tag already exists
                Optional<Tag> existingTag = tagRepository.findByName(tag.getName());
                if (existingTag.isPresent()) {
                    book.addTag(existingTag.get());
                } else {
                    tag.getBooks().add(book);
                    book.addTag(tag);
                }
            }
        }

        // Remove tags by ID
        if (update.getRemoveTagIds() != null && !update.getRemoveTagIds().isEmpty()) {
            List<Tag> tagsToRemove = book.getTags().stream()
                .filter(tag -> update.getRemoveTagIds().contains(tag.getId()))
                .collect(Collectors.toList());
            
            tagsToRemove.forEach(tag -> {
                book.removeTag(tag);
                tag.getBooks().remove(book);
            });
        }
    }

    public List<Book> getAllBooks() {
        logger.info("Retrieving all books");
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String title, String author, ReadingStatus status, 
                                Integer rating, String tagName, String startDate, String endDate) {
        logger.info("Searching books with filters: title={}, author={}, status={}, rating={}, tagName={}, startDate={}, endDate={}",
                   title, author, status, rating, tagName, startDate, endDate);
        
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        }
        
        if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        }
        
        if (status != null) {
            return bookRepository.findByStatus(status);
        }
        
        if (rating != null) {
            return bookRepository.findByRating(rating);
        }
        
        if (tagName != null && !tagName.isEmpty()) {
            return bookRepository.findByTagName(tagName);
        }
        
        if (startDate != null && !startDate.isEmpty()) {
            return bookRepository.findByDateStartedAfter(startDate);
        }
        
        if (endDate != null && !endDate.isEmpty()) {
            return bookRepository.findByDateFinishedBefore(endDate);
        }
        
        return getAllBooks();
    }

    public Optional<Book> getBookById(Long id) {
        logger.info("Retrieving book with ID: {}", id);
        return bookRepository.findById(id);
    }

    @Transactional
    public void deleteBook(Long id) {
        logger.info("Deleting book with ID: {}", id);
        if (!bookRepository.existsById(id)) {
            logger.error("Book not found with ID: {}", id);
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        logger.info("Book deleted successfully with ID: {}", id);
    }

}
