package com.sanfosx.ApiRestJavaInfo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JsonProperty(value = "emprendimiento_id")
    private Long emprendimentId;
    @JsonProperty(value = "user_id")
    private Long userId;
    //fecha de creacion
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime date;
    //generado desde
    @NotNull
    @JsonProperty(value = "generated_from")
    @Enumerated(EnumType.STRING)
    private GeneratedFrom generatedFrom;

    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmprendimentId() {
        return emprendimentId;
    }

    public void setEmprendimentId(Long emprendimentId) {
        this.emprendimentId = emprendimentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public GeneratedFrom getGeneratedFrom() {
        return generatedFrom;
    }

    public void setGeneratedFrom(GeneratedFrom generatedFrom) {
        this.generatedFrom = generatedFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        Vote vote = (Vote) o;
        return getId().equals(vote.getId()) && getEmprendimentId().equals(vote.getEmprendimentId()) && getUserId().equals(vote.getUserId()) && getDate().equals(vote.getDate()) && getGeneratedFrom() == vote.getGeneratedFrom();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmprendimentId(), getUserId(), getDate(), getGeneratedFrom());
    }
}