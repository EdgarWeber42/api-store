package com.supinfo.proj.retailr.apistore.service;

import com.supinfo.proj.retailr.apistore.data.entity.Event;
import com.supinfo.proj.retailr.apistore.data.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    public Collection<Event> search(String type, Long storeId, Long deviceId){
        Collection<Event> events = this.eventRepository.findAll();
        if (type != null && !type.isEmpty()){
            logger.info("type is not null " + type);
            events.removeIf(event -> !event.getType().equals(type));
        }
        if (deviceId != null){
            logger.info("deviceId is not null " + deviceId);
            events.removeIf(event -> event.getDevice().getId() != deviceId);
        }
        return events;
    }

    public void createEvent(Event event) throws Exception {
        try {
            this.eventRepository.save(event);
        } catch (Exception e) {
            throw new Exception("couldn't save event : " + e.getCause());
        }
    }
}
