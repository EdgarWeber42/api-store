package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.repository.EventItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventItemController {

    private static final Logger logger = LoggerFactory.getLogger(EventItemController.class);

    @Autowired
    private EventItemRepository eventItemRepository;

    @GetMapping("/eventitems")
    public ResponseEntity<?> getEventItems() {
        return ResponseEntity.ok().body(this.eventItemRepository.findAll());
    }

}
