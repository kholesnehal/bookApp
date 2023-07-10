package com.book.book.service.impl;

import com.book.book.entity.Publisher;
import com.book.book.exception.NotFoundException;
import com.book.book.repository.PublisherRepository;
import com.book.book.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;
    @Override
    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));

    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        Publisher updatedPublisher=publisherRepository.save(publisher);
        return updatedPublisher;
    }

    @Override
    public void deletePublisher(Long id) {
        final Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Publisher not found  with ID %d", id)));

        publisherRepository.deleteById(publisher.getPublisherId());
    }
}
