package com.book.book.service;

import com.book.book.entity.Publisher;

import java.util.List;

public interface PublisherService {

    public List<Publisher> findAllPublishers();

    public Publisher findPublisherById(Long id);

    public Publisher addPublisher(Publisher publisher);

    public Publisher updatePublisher(Publisher publisher);

    public void deletePublisher(Long id);
}
