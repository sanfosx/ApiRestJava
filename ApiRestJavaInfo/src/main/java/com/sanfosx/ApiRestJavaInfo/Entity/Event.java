package com.sanfosx.ApiRestJavaInfo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //nombre
    @NotEmpty(message = "El nombre no puede ser vacio")
    @Size(min = 3, max = 30)
    private String name;
    //nombre
    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(length = 1000)
    private String details;
    //fecha de creacion
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate created;
    //fecha de fin de incripciones al evento
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate registrationClosure;
    //fecha de fin del evento
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;
    //estado del evento
    @Column(name="status", nullable = false, columnDefinition = "varchar(32) default 'OPEN'")
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.OPEN;
    //relacion con el emprendimiento
    @ManyToMany(mappedBy = "events")
    @JsonIgnoreProperties({"description","content","created","objetive","published","tags" })
    @OrderBy("votesCount DESC")
    private List<Emprendiment> emprendiments = new ArrayList<>();
    //premio del evento
    @NotNull
    @Positive
    private Double winnerReward;

    public Event() {
    }
    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getRegistrationClosure() {
        return registrationClosure;
    }

    public void setRegistrationClosure(LocalDate registrationClosure) {
        this.registrationClosure = registrationClosure;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {return status;}

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Emprendiment> getEmprendiments() {
        return emprendiments;
    }

    public void setEmprendiments(List<Emprendiment> emprendiments) {
        this.emprendiments = emprendiments;
    }

    public Double getWinnerReward() {
        return winnerReward;
    }

    public void setWinnerReward(Double winnerReward) {
        this.winnerReward = winnerReward;
    }

    public void removeEmprendiment(Emprendiment emprendiment) {
        emprendiments.remove(emprendiment);
        emprendiment.getEvents().remove(null);
    }

    public void addEmprendiment(Emprendiment emprendiment) {
        emprendiments.add(emprendiment);
        emprendiment.getEvents().add(this);
    }
}