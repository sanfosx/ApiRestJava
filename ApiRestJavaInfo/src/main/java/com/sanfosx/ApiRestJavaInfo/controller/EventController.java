package com.sanfosx.ApiRestJavaInfo.controller;

import com.sanfosx.ApiRestJavaInfo.Entity.Event;
import com.sanfosx.ApiRestJavaInfo.Entity.User;
import com.sanfosx.ApiRestJavaInfo.repository.EventRepository;
import com.sanfosx.ApiRestJavaInfo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private final EventService eventService;
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository, EventService eventService) {
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }
    //crear evento
    @PostMapping
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.CREATED);
    }
    //obtener todos los eventos
    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }
    //obtener evento por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        return new ResponseEntity<>(eventRepository.findById(id), HttpStatus.OK);
    }
    //borrar evento
    @DeleteMapping("/remove/{id}")
    public void removeEvent(@PathVariable Long id){
        Event event = eventRepository.getById(id);
        this.eventService.removeEvent(id, event);
    }

    //ranking de emprendimiento por evento segun la cantidad de votos
    @GetMapping("/ranking/{id}")
    public ResponseEntity<?> getEventRanking(
            @PathVariable Long id) {
        return new ResponseEntity<>(eventRepository.findById(id), HttpStatus.OK);
    }
    //Modificar Estado del Evento
    @PutMapping("/update-status")
    public void updateStatus(){
        this.eventService.update();
    }
}
