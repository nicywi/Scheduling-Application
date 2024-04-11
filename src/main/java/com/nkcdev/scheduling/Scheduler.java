package com.nkcdev.scheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Scheduler class for scheduling logic
public class Scheduler {
    private Map<String, Person> persons; // Mapping of email to Person object
    private List<Meeting> meetings; // List to store scheduled meetings

    public Scheduler() {
        persons = new HashMap<>();
        meetings = new ArrayList<>();
    }

    // Create a new person with name and email
    public void createPerson(String name, String email) {
        if (!persons.containsKey(email)) {
            persons.put(email, new Person(name, email));
            System.out.println("Person created: " + name);
        } else {
            System.out.println("Email already exists.");
        }
    }

    // Create a new meeting with participants and start time
    public void createMeeting(List<Person> participants, LocalDateTime startTime) {
        if (startTime.getMinute() != 0 || startTime.getSecond() != 0 || startTime.getNano() != 0) {
            System.out.println("Meeting can only start at the hour mark. Please try again.");
            return;
        }
        meetings.add(new Meeting(participants, startTime));
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println("Meeting scheduled at " + formattedStartTime);
    }

    // Show upcoming meetings for a given person
    public void showSchedule(String email) {
        if (!persons.containsKey(email)) {
            System.out.println("Person with email " + email + " not found.");
            return;
        }

        System.out.println("Schedule for " + persons.get(email).getName() + ":");
        for (Meeting meeting : meetings) {
            if (meeting.getParticipants().contains(persons.get(email))) {
                String formattedStartTime = meeting.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                System.out.println("Meeting at " + formattedStartTime);
            }
        }
    }
    // Suggest available timeslots for a meeting given a group of persons
    public void suggestTimeslots(List<Person> participants) {
        System.out.println("Available timeslots for meeting:");
        for (int hour = 0; hour <= 23; hour++) {
            LocalDateTime startTime = LocalDateTime.now().withHour(hour).withMinute(0).withSecond(0).withNano(0);

            boolean available = true;
            for (Meeting meeting : meetings) {
                LocalDateTime meetingStart = meeting.getStartTime();

                // Check if the proposed meeting slot overlaps with any existing meeting
                if (startTime.isEqual(meetingStart)) {
                    available = false;
                    break;
                }
            }
            if (available) {
                System.out.println(startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }
        }
    }

    // Getter for persons
    public Map<String, Person> getPersons() {
        return persons;
    }

    // Getter for meetings
    public List<Meeting> getMeetings() {
        return meetings;
    }
}
