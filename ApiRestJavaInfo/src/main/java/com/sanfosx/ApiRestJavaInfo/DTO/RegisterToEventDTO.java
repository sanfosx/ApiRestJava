package com.sanfosx.ApiRestJavaInfo.DTO;

public class RegisterToEventDTO {

    private Long eventId;
    private Long subscriberId;
    //getters y setters
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    public Long getSubscriberId() {
        return subscriberId;
    }
    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }
}
