package com.nkcdev.scheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// SchedulingApp class for user interactions
public class SchedulingApp {
    public static void main(String[] args) {

//        EXAMPLE FOR TEST
        Scheduler scheduler = new Scheduler();

        // Create persons
        scheduler.createPerson("Alice", "alice@example.com");
        scheduler.createPerson("Bob", "bob@example.com");

        // Create meetings
        List<Person> participants = new ArrayList<>();
        participants.add(scheduler.getPersons().get("alice@example.com"));
        participants.add(scheduler.getPersons().get("bob@example.com"));
        LocalDateTime startTime = LocalDateTime.now().plusDays(0).withHour(10).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startTime2 = LocalDateTime.now().plusDays(0).withHour(11).withMinute(0).withSecond(0).withNano(0);
        scheduler.createMeeting(participants, startTime);
        scheduler.createMeeting(participants, startTime2);

        // Show Alice's schedule
        scheduler.showSchedule("alice@example.com");

        // Suggest available timeslots
        scheduler.suggestTimeslots(participants);
    }
}


