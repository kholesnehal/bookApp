package com.book.book.service.impl;

import com.book.book.entity.Book;
import com.book.book.entity.User;
import com.book.book.exception.NotFoundException;
import com.book.book.repository.UserRepository;
import com.book.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User not found with ID %d", userId)));

    }
    }

