package com.example.tbs.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tbs.inventoryservice.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
}
