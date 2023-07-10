package com.book.book.service.impl;





import com.book.book.entity.Book;
import com.book.book.entity.User;
import com.book.book.exception.NotFoundException;
import com.book.book.repository.BookRepository;
import com.book.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private List<User> users;
    @Override
    public List<Book> getAllBooks() {
     return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }


    @Override
    public Book findBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", bookId)));

    }

    @Override
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
       Book book1= bookRepository.save(book);
        return book1;
    }


    @Override
    public void deleteBook(Integer bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", bookId)));

        bookRepository.deleteById(book.getBookId());
    }

    @Override
    public void makeRequest(User user, Book book) {
        if (book.isAvailable()) {
            // If the book is available, assign it to the user
            user.addOwnedBook(book);
            book.setAvailable(false);
            book.decreaseNumCopies();
        } else {
            // If the book is not available, add it to the user's requested books list
            user.addRequestedBook(book);
        }
    }
    @Override
    // Return a book to the available books list
    public void returnBook(User user, Book book) {
        user.removeOwnedBook(book);
        book.setAvailable(true);
        book.increaseNumCopies();

        // Check if any user has requested the returned book
        for (User requestedUser : users) {
            if (requestedUser.getRequestedBooks().contains(book)) {
                requestedUser.addOwnedBook(book);
                requestedUser.removeRequestedBook(book);
                book.setAvailable(false);
                book.decreaseNumCopies();
                break;
            }
        }
    }
}
