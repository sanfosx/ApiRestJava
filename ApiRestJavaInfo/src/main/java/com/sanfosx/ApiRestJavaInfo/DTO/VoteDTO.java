package com.sanfosx.ApiRestJavaInfo.DTO;

import com.sanfosx.ApiRestJavaInfo.Entity.GeneratedFrom;
import java.time.LocalDateTime;

public class VoteDTO {

    private Long userId;
    private Long emprendimentId;
    private LocalDateTime date;
    private GeneratedFrom generatedFrom;
    private boolean voted;
    //getters y setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmprendimentId() {
        return emprendimentId;
    }

    public void setEmprendimentId(Long emprendimentId) {
        this.emprendimentId = emprendimentId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public GeneratedFrom getGeneratedFrom() {
        return generatedFrom;
    }

    public void setGeneratedFrom(GeneratedFrom generatedFrom) {
        this.generatedFrom = generatedFrom;
    }
}
