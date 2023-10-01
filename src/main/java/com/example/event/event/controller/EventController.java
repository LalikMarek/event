package com.example.event.event.controller;

import com.example.event.event.service.EventDetailDTO;
import com.example.event.event.service.EventRequestDTO;
import com.example.event.event.service.EventService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/api/events")
    public List<EventDetailDTO> searchEvent(){
        System.out.println("Search events.");

        return eventService.getAllEvents();
    }

    @GetMapping("/api/events/{eventId}")
    public EventDetailDTO getEventById(@PathVariable Long eventId){
        System.out.println("Search event.");

        return eventService.getEventById(eventId);
    }

    @PostMapping("/api/events")
    public Long createEvent(@Valid @RequestBody EventRequestDTO eventRequestDTO){
        System.out.println("Create event.");

        return eventService.createEvent(eventRequestDTO);
    }

    @PutMapping("/api/events/{eventId}")
    public void updateEvent(@Valid @PathVariable Long eventId, @RequestBody EventRequestDTO eventRequestDTO){
        System.out.println("Update event.");

        eventService.updateEvent(eventRequestDTO, eventId);
    }

    @DeleteMapping("/api/events/{eventId}")
    public void deleteEvent(@PathVariable Long eventId){
        System.out.println("Delete event.");

        eventService.deleteEvent(eventId);
    }
}
