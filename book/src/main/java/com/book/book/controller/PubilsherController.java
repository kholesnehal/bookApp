package com.book.book.controller;

import com.book.book.entity.Author;
import com.book.book.entity.Publisher;
import com.book.book.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PubilsherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/publishers")
    public List<Publisher> findAllPublishers()
    {
        List<Publisher> publishers= publisherService.findAllPublishers();
        return publishers;
    }

    @GetMapping("/publisher/{id}")
    public Publisher findPublisherById(@PathVariable("id") Long id)
    {
       Publisher publisher=publisherService.findPublisherById(id);
       return publisher;
    }

    @PostMapping("/addPublisher")
    public String addPublisher(@RequestBody Publisher publisher)
    {
        publisherService.addPublisher(publisher);
        return "publisher added successfully";
    }

    @PutMapping("/{publisherId}")
    public ResponseEntity<String> updatePublisher(@PathVariable("publisherId") Long publisherId, @RequestBody Publisher updatedPublisher){

        Publisher existingPublisher = publisherService.findPublisherById(publisherId);

        if (existingPublisher == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the author's properties
        existingPublisher.setPublisherName(updatedPublisher.getPublisherName());



        // Save the updated author
        publisherService.updatePublisher(existingPublisher);

        return ResponseEntity.ok("publisher updated successfully");
    }

    @DeleteMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
        return "Publisher delete successfully!";
    }
}
