package com.book.book.repository;

import com.book.book.entity.Author;
import com.book.book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
