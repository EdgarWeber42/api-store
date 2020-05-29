package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    Iterable<Event> getEventByType(String type);
    Collection<Event> findAll();
}
