package com.bookkeeper.bookkeeper_backend.Controller;

import com.bookkeeper.bookkeeper_backend.BookUpdateDTO;
import com.bookkeeper.bookkeeper_backend.Model.Book;
import com.bookkeeper.bookkeeper_backend.Model.ReadingStatus;
import com.bookkeeper.bookkeeper_backend.Service.BookService;
import com.bookkeeper.bookkeeper_backend.Exception.BookNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
@Tag(name = "Book Controller", description = "APIs for managing books")
public class BookController {
    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Operation(summary = "Add a new book", description = "Creates a new book with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/search")
    @Operation(summary = "Search books", description = "Search books by various criteria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
    public ResponseEntity<List<Book>> searchBooks(
            @Parameter(description = "Title of the book") @RequestParam(required = false) String title,
            @Parameter(description = "Author of the book") @RequestParam(required = false) String author,
            @Parameter(description = "Reading status") @RequestParam(required = false) ReadingStatus status,
            @Parameter(description = "Rating (1-5)") @RequestParam(required = false) Integer rating,
            @Parameter(description = "Tag name") @RequestParam(required = false) String tagName,
            @Parameter(description = "Start date (yyyy-MM-dd)") @RequestParam(required = false) String startDate,
            @Parameter(description = "End date (yyyy-MM-dd)") @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok(bookService.searchBooks(title, author, status, rating, tagName, startDate, endDate));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieves a specific book by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book found"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "ID of the book to retrieve") @PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a book", description = "Partially updates a book with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Book> partiallyUpdateBook(
            @Parameter(description = "ID of the book to update") @PathVariable Long id,
            @RequestBody BookUpdateDTO updatedBookDetails) {
        return ResponseEntity.ok(bookService.updateBook(id, updatedBookDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}

