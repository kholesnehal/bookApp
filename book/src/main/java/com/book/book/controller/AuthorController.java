package com.book.book.controller;

import com.book.book.entity.Author;
import com.book.book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @GetMapping("/authors")
    public List<Author> findAllAuthors()
    {
       List<Author> authors= authorService.findAllAuthors();
       return authors;
    }

    @GetMapping("/author/{id}")
    public Author findAuthorById(@PathVariable("id") Long id)
    {
        final Author author = authorService.findAuthorById(id);
        return author;
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody Author author)
    {
        authorService.addAuthor(author);
        return "Author added successfully";
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<String> updateAuthor(@PathVariable("authorId") Long authorId, @RequestBody Author updatedAuthor){

            Author existingAuthor = authorService.findAuthorById(authorId);

            if (existingAuthor == null) {
                return ResponseEntity.notFound().build();
            }

            // Update the author's properties
            existingAuthor.setName(updatedAuthor.getName());
            existingAuthor.setDescription(updatedAuthor.getDescription());


            // Save the updated author
            authorService.updateAuthor(existingAuthor);

            return ResponseEntity.ok("Author updated successfully");
        }

    @DeleteMapping("/remove-author/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
       authorService.deleteAuthor(id);
        return "author delete successfully!";
    }
    }

