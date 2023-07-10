package com.book.book.service;

import com.book.book.entity.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> findAllAuthors();

    public Author findAuthorById(Long id);

    public Author addAuthor(Author author);

    public Author updateAuthor(Author author);

    public void deleteAuthor(Long id);
}
