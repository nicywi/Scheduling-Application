package com.nkcdev.scheduling;

import java.time.LocalDateTime;
import java.util.List;

// Meeting class representing a meeting with participants and start time
public class Meeting {
    private List<Person> participants;
    private LocalDateTime startTime;

    public Meeting(List<Person> participants, LocalDateTime startTime) {
        this.participants = participants;
        this.startTime = startTime;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
