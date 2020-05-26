package com.supinfo.proj.retailr.apistore.data.repository;

import com.supinfo.proj.retailr.apistore.data.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
