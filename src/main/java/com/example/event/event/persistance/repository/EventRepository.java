package com.example.event.event.persistance.repository;

import com.example.event.event.persistance.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {
    Iterable<EventEntity> findByName(String name);

    EventEntity getEventEntityById(Long eventId);
}
