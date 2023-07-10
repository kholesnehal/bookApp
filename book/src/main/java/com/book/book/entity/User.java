package com.book.book.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
public class User {
        @Id
        private Integer userId;
        private String name;
       @OneToMany(cascade = CascadeType.ALL)
        private List<Book> ownedBooks;
       @OneToMany(cascade = CascadeType.ALL)
        private List<Book> requestedBooks;

        // Constructor
        public User(String name) {
            this.name = name;
            this.ownedBooks = new ArrayList<>();
            this.requestedBooks = new ArrayList<>();
        }

    public User() {

    }

    // Getters and setters

        public String getName() {
            return name;
        }

        public List<Book> getOwnedBooks() {
            return ownedBooks;
        }

        public List<Book> getRequestedBooks() {
            return requestedBooks;
        }

        // Other methods

        public void addOwnedBook(Book book) {
            ownedBooks.add(book);
        }

        public void removeOwnedBook(Book book) {
            ownedBooks.remove(book);
        }

        public void addRequestedBook(Book book) {
            requestedBooks.add(book);
        }

        public void removeRequestedBook(Book book) {
            requestedBooks.remove(book);
        }
    }


