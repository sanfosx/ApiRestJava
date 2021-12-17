package com.sanfosx.ApiRestJavaInfo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emprendiment {
    //clave
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //nombre
    @NotEmpty(message = "El nombre no puede ser vacio")
    @Size(min = 3, max = 30)
    private String name;
    //descripcion
    @NotEmpty(message = "La descripcion no puede ser vacio")
    @Size(min = 3, max = 500)
    private String description;
    //contenido
    @NotEmpty(message = "El contenido no puede ser vacio")
    @Size(min = 3, max = 500)
    private String content;
    //publicar
    @NotNull(message = "Publicar debe ser True o False")
    private Boolean published;
    //objetivo en $
    @NotNull(message = "Debe ingresar un valor mayor a 0 para el objetivo")
    @Positive
    private Double  objective;
    //fecha de creacion
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime createdDate;
    //relacion con el usuario
    @ManyToOne(fetch = FetchType.EAGER)
    private User userId;
    //relacion de tag
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "emprediments_tags",
            joinColumns = @JoinColumn(name = "emprendiment_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
    // relacion de votos
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "emprendimentId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();
    private Integer votesCount = 0;
    //relacion de eventos
    @JoinTable(
            name = "events_emprendiments",
            joinColumns = {@JoinColumn(name = "emprendiment_id",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id",nullable = false)}
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Event> events = new ArrayList<>();
    //getters and setters
    public Long getId() {
        return id;
    }

    /*public void setIdEmprendiment(Long idEmprendiment) {// no se debe setear el id ya que es autogenerado
        this.idEmprendiment = idEmprendiment;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Double getObjective() {
        return objective;
    }

    public void setObjective(Double objective) {
        this.objective = objective;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getEmprendiments().remove(null);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getEmprendiments().add(this);
    }

    public int getVotes() {

        return votes.size();
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Integer getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(Integer votesCount) {
        this.votesCount = votesCount;
    }

    public void addEvent(Event event){
        if(this.events == null){
            this.events = new ArrayList<>();
        }
        this.events.add(event);
    }
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

