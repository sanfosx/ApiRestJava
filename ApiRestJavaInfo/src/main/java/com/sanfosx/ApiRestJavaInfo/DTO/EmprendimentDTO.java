package com.sanfosx.ApiRestJavaInfo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class EmprendimentDTO {

    @NotEmpty(message = "El nombre no puede ser vacio")
    private String name;

    @NotEmpty(message = "la descripcion no puede ser vacio")
    private String description;

    @NotEmpty(message = "El contenido no puede ser vacio")
    private String content;

    @NotNull(message = "Published debe ser True o False")
    private Boolean published;

    @NotNull
    @Positive
    private Double objective;

    private String Username;
    @NotNull
    @Positive
    @JsonProperty(value = "user_id")
    private Long userId;

    private List<Long> tags;

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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

