package com.supinfo.proj.retailr.apistore.data.entity;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@Table(name = "event_items")
public class EventItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "event_id")
    private long eventId;

    @Column(name = "item_epc")
    private String epc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }
}
