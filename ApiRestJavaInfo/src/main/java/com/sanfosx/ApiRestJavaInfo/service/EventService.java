package com.sanfosx.ApiRestJavaInfo.service;

import com.sanfosx.ApiRestJavaInfo.DTO.RegisterToEventDTO;
import com.sanfosx.ApiRestJavaInfo.DTO.EventDTO;
import com.sanfosx.ApiRestJavaInfo.Entity.*;
import com.sanfosx.ApiRestJavaInfo.repository.EmprendimentRepository;
import com.sanfosx.ApiRestJavaInfo.repository.EventRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@Configuration
@EnableScheduling
public class EventService {

    private final EventRepository eventRepository;
    private final EmprendimentRepository emprendimentRepository;;

    public EventService(EventRepository eventRepository, EmprendimentRepository emprendimentRepository) {
        this.eventRepository = eventRepository;
        this.emprendimentRepository = emprendimentRepository;
    }

    public void update() {
        List<Event> events = eventRepository.findAll();
        LocalDate now = LocalDate.now();
        events.stream()
                .forEach(event -> actualizarStatus(event, now));
        eventRepository.saveAll(events);
        System.out.println("Updated event statuses.");
    }

    private void actualizarStatus(Event event, LocalDate now) {
        if (event.getRegistrationClosure().isBefore(now)) {
            event.setStatus(Status.IN_COURSE);
        }
        if (event.getEndDate().isBefore(now)) {
            event.setStatus(Status.FINALIZED);
        }
    }

    public Event createEvent(EventDTO eventDTO) {

        List<Emprendiment> emprendiments = emprendimentRepository.findAllById(eventDTO.getEmprendiments());
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDetails(eventDTO.getDetails());
        event.setWinnerReward(eventDTO.getWinnerReward());
        event.setRegistrationClosure(eventDTO.getRegistrationClosure());
        event.setEndDate(eventDTO.getEndDate());
        event.getEmprendiments().addAll(emprendiments);

        return eventRepository.save(event);
    }



    public void removeEvent(Long id, Event event) {
        Event inDB = eventRepository.getById(id);
        eventRepository.delete(inDB);
    }
    public Event updateEvent(Long id, Event event) {
        Event inDB = eventRepository.getById(id);
        if(event.getName() != null){
            inDB.setName(event.getName());
        }
        if(event.getDetails() != null){
            inDB.setDetails(event.getDetails());
        }
        if(event.getWinnerReward() != null) {
            inDB.setWinnerReward(event.getWinnerReward());
        }
        if(event.getEndDate() != null) {
            inDB.setEndDate(event.getEndDate());
        }
        if(event.getRegistrationClosure() != null) {
            inDB.setRegistrationClosure(event.getRegistrationClosure());
        }

        return eventRepository.save(inDB);
    }

    public Emprendiment register(Long empId, Long eventId, RegisterToEventDTO registerToEventDTO) {
        Emprendiment emprendiment1 = emprendimentRepository.getById(empId);
        Event event1 = eventRepository.getById(eventId);
        if(event1.getStatus() == Status.OPEN) {
            emprendiment1.addEvent(event1);
            System.out.println("Se ha subscrito a este evento correctamente.");
        } else{
            System.out.println("No es posible Suscribirse");
        }
        return emprendimentRepository.save(emprendiment1);
    }
}