package com.supinfo.proj.retailr.apistore.data.controller;

import com.supinfo.proj.retailr.apistore.data.entity.Event;
import com.supinfo.proj.retailr.apistore.data.model.Response;
import com.supinfo.proj.retailr.apistore.data.repository.EventRepository;
import com.supinfo.proj.retailr.apistore.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<?> getEvents(@RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "store_id", required = false) Long storeId,
                                             @RequestParam(value = "device_id", required = false) Long deviceId,
                                             @RequestParam(value = "customer_id", required = false) Long customerId,
                                             @RequestParam(value = "staff_id", required = false) Long staff_id) {
        Collection<Event> events = this.eventService.search(type, storeId, deviceId);
        return ResponseEntity.ok().body(events);
    }
}
