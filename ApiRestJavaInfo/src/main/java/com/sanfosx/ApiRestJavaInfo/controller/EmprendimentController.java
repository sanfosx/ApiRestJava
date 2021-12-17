package com.sanfosx.ApiRestJavaInfo.controller;

import com.sanfosx.ApiRestJavaInfo.DTO.EmprendimentDTO;
import com.sanfosx.ApiRestJavaInfo.DTO.RegisterToEventDTO;
import com.sanfosx.ApiRestJavaInfo.Entity.Emprendiment;
import com.sanfosx.ApiRestJavaInfo.repository.EmprendimentRepository;
import com.sanfosx.ApiRestJavaInfo.repository.EventRepository;
import com.sanfosx.ApiRestJavaInfo.service.EmprendimentService;
import com.sanfosx.ApiRestJavaInfo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/emprendiment")
public class EmprendimentController {

    private final EmprendimentService emprendimentService;
    private final EmprendimentRepository emprendimentRepository;
    private final EventRepository eventRepository;
    private final EventService eventService;

    @Autowired
    public EmprendimentController(EmprendimentService emprendimentService, EmprendimentRepository emprendimentRepository, EventRepository eventRepository, EventService eventService) {
        this.emprendimentService = emprendimentService;
        this.emprendimentRepository = emprendimentRepository;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }
    //crear emprendimiento
    @PostMapping
    public ResponseEntity<?> createEmprendiment(@Valid @RequestBody EmprendimentDTO emprendimentDTO) {
        return new ResponseEntity<>(emprendimentService.createEmprendiment(emprendimentDTO), HttpStatus.CREATED);
    }
    //modificar emprendimiento por Id
    @PutMapping("/update/{id}")
    public Emprendiment updateEmprendiment(@PathVariable Long id, @RequestBody Emprendiment emprendiment){
        return this.emprendimentService.updateEmprendiment(id, emprendiment);
    }
    //borrar emprendimiento por Id
    @DeleteMapping("remove/{id}")
    public void removeEmprendiment(@PathVariable Long id){
        Emprendiment emprendiment = emprendimentRepository.getById(id);
        this.emprendimentService.removeEmprendiment(id, emprendiment);
    }
    //obtener todos los emprendimientos
    @GetMapping
    public ResponseEntity<?> getAllEmprendimientos(
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "published", required = false, defaultValue = "true") boolean published) {
        if(tag != null){
            return new ResponseEntity<>(emprendimentRepository.findByTag(tag), HttpStatus.OK);
        } else if (!published) {
            return new ResponseEntity<>(emprendimentRepository.findByPublished(false), HttpStatus.OK);
        }
        return new ResponseEntity<>(emprendimentRepository.findByPublished(true), HttpStatus.OK);
    }
    //obtener emprendimiento por Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmprendimentById(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(emprendimentRepository.findById(id), HttpStatus.OK);
    }
    //registrar emprendimiento a un evento
    @PostMapping("{empId}/events/{eventId}")
    public ResponseEntity<?> register(@PathVariable("empId") Long empId,
                                      @PathVariable("eventId") Long eventId, RegisterToEventDTO registerToEventDTO) {
        emprendimentRepository.findById(empId);
        eventRepository.findById(eventId);
        return new ResponseEntity<>(eventService.register(empId, eventId, registerToEventDTO), HttpStatus.CREATED);
    }
}
