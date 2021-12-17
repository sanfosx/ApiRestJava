package com.sanfosx.ApiRestJavaInfo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

public class EventDTO {

    @NotEmpty(message = "El nombre no puede ser vacio")
    private String name;

    @NotEmpty(message = "El descripcion no puede ser vacio")
    private String details;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate registrationClosure;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;

    @NotNull
    @Positive
    private Double winnerReward;

    private List<Long> emprendiments;

    //getter y setters
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

    public Double getWinnerReward() {
        return winnerReward;
    }

    public void setWinnerReward(Double winnerReward) {
        this.winnerReward = winnerReward;
    }

    public List<Long> getEmprendiments() {
        return emprendiments;
    }

    public void setEmprediments(List<Long> emprendiments) {
        this.emprendiments = emprendiments;
    }
}
