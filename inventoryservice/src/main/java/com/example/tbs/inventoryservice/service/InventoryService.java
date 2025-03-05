package com.example.tbs.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tbs.inventoryservice.entity.Event;
import com.example.tbs.inventoryservice.entity.Venue;
import com.example.tbs.inventoryservice.repository.EventRepository;
import com.example.tbs.inventoryservice.repository.VenueRepository;
import com.example.tbs.inventoryservice.response.EventInventoryResponse;
import com.example.tbs.inventoryservice.response.VenueInventoryResponse;

@Service
public class InventoryService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private final VenueRepository venueRepository;
    
    public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public List<EventInventoryResponse> getAllEvents() {
        final List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueInformation(Long venueId) {
        final Venue venue = venueRepository.findById(venueId).orElse(null);

        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
                
    }
}
