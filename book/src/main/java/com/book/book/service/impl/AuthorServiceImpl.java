package com.book.book.service.impl;

import com.book.book.entity.Author;
import com.book.book.exception.NotFoundException;
import com.book.book.repository.AuthorRepository;
import com.book.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));

    }

    @Override
    public Author addAuthor(Author author) {
       return authorRepository.save(author);

    }

    @Override
    public Author updateAuthor(Author author) {
       return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        final Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));

        authorRepository.deleteById(author.getAuthorId());
    }
}
