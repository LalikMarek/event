package com.example.event.event.service;

import com.example.event.event.persistance.entity.EventEntity;
import com.example.event.event.persistance.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDetailDTO> getAllEvents() {
        return mapToDtoList(eventRepository.findAll());
    }

    public EventDetailDTO getEventById(Long eventId) {
        return mapToDto(eventRepository.getEventEntityById(eventId));
    }

    public Long createEvent(EventRequestDTO eventRequestDTO) {
        EventEntity entity = mapToEntity(eventRequestDTO);

        return eventRepository.save(entity).getId();
    }

    @Transactional
    public void updateEvent(EventRequestDTO eventRequestDTO, Long eventId) {
        EventEntity entity = eventRepository.getEventEntityById(eventId);

        if(!Strings.isEmpty(eventRequestDTO.getName())){
            entity.setName(eventRequestDTO.getName());
        }

        eventRepository.save(entity);
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        EventEntity entity = eventRepository.getEventEntityById(eventId);

        eventRepository.deleteById(eventId);
    }

    private EventEntity mapToEntity(EventRequestDTO eventRequestDTO) {
        EventEntity entity = new EventEntity();

        entity.setName(eventRequestDTO.getName());

        return entity;
    }

    private List<EventDetailDTO> mapToDtoList(Iterable<EventEntity> entities) {
        List<EventDetailDTO> events = new ArrayList<>();

        entities.forEach(eventEntity -> {
            EventDetailDTO dto = mapToDto(eventEntity);
            events.add(dto);
        });

        return events;
    }

    private EventDetailDTO mapToDto(EventEntity eventEntity) {
        EventDetailDTO dto = new EventDetailDTO();

        dto.setId(eventEntity.getId());
        dto.setName(eventEntity.getName());

        return dto;
    }

}
