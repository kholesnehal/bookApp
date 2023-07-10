package com.book.book.controller;

import com.book.book.entity.Book;
import com.book.book.entity.User;
import com.book.book.repository.BookRepository;
import com.book.book.repository.UserRepository;
import com.book.book.service.BookService;
import com.book.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }


    @GetMapping("/books")
    public List<Book> findAllBooks() {
        final List<Book> books = bookService.getAllBooks();
        return books;
    }

    @PostMapping("/searchBook")
    public List<Book> searchBook(@Param("keyword") String keyword) {
        final List<Book> books = bookService.searchBooks(keyword);
        return books;
    }

    @GetMapping("/book/{id}")
    public Book findBookById(@PathVariable("id") Integer id) {
        final Book book = bookService.findBookById(id);
        return book;
    }


    @PostMapping("/add-book")
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "book added successfully!";
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody Book updatedBook) {
        Book existingBook = bookService.findBookById(bookId);

        if (existingBook == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the existing book with the new information
        existingBook.setBookName(updatedBook.getBookName());
        existingBook.setSerialName(updatedBook.getSerialName());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setAvailable(updatedBook.isAvailable());
        existingBook.setNumCopies(updatedBook.getNumCopies());

        // Save the updated book
        bookService.updateBook(existingBook);

        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "book delete successfully!";
    }

    @PostMapping("/request/{bookId}")
    public ResponseEntity<String> makeBookRequest(@PathVariable("bookId") Integer bookId, @RequestParam("userId") Integer userId) {
        Book book = bookService.findBookById(bookId);
        User user = userService.getUserById(userId);

        if (book == null || user == null) {
            return ResponseEntity.badRequest().body("Invalid bookId or userId");
        }

        // Make the book request
        bookService.makeRequest(user, book);

        return ResponseEntity.ok("Book request made successfully");
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable("bookId") Integer bookId, @RequestParam("userId") Integer userId) {
        Book book = bookService.findBookById(bookId);
        User user = userService.getUserById(userId);

        if (book == null || user == null) {
            return ResponseEntity.badRequest().body("Invalid bookId or userId");
        }

        // Return the book
        bookService.returnBook(user, book);

        return ResponseEntity.ok("Book returned successfully");
    }
}
