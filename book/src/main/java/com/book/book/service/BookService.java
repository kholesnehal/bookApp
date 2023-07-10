package com.book.book.service;

import com.book.book.entity.Book;
import com.book.book.entity.User;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    List<Book> searchBooks(String keyword);
    public Book findBookById(Integer bookId);

    public Book addBook(Book book);


    public Book updateBook(Book book);

    public void deleteBook(Integer bookId);

    public void makeRequest(User user, Book book);

    public void returnBook(User user, Book book);











}
