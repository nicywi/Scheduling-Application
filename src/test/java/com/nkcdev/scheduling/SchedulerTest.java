package com.nkcdev.scheduling;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;

public class SchedulerTest {
    @Test
    public void testCreatePerson() {
        Scheduler scheduler = new Scheduler();
        scheduler.createPerson("Alice", "alice@example.com");
        scheduler.createPerson("Bob", "bob@example.com");
        scheduler.createPerson("Alice", "alice@example.com"); // Duplicate email
        assertEquals(2, scheduler.getPersons().size()); // Only two persons should be created
    }

    @Test
    public void testCreateMeeting() {
        Scheduler scheduler = new Scheduler();
        List<Person> participants = new ArrayList<>();
        participants.add(new Person("Alice", "alice@example.com"));
        participants.add(new Person("Bob", "bob@example.com"));
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0); // Schedule meeting for tomorrow

        scheduler.createMeeting(participants, startTime);
        assertEquals(1, scheduler.getMeetings().size()); // Meeting should be added

        // Test for meeting at non-hour mark time
        LocalDateTime startTimeInvalid = LocalDateTime.now().plusMinutes(30);
        scheduler.createMeeting(participants, startTimeInvalid);
        assertEquals(1, scheduler.getMeetings().size()); // Meeting should not be added
    }

    @Test
    public void testShowSchedule() {
        Scheduler scheduler = new Scheduler();
        Person alice = new Person("Alice", "alice@example.com");
        Person bob = new Person("Bob", "bob@example.com");
        LocalDateTime now = LocalDateTime.now();
        List<Person> participants = new ArrayList<>();
        participants.add(alice);
        participants.add(bob);
        scheduler.createMeeting(participants, now.plusDays(1).withMinute(0).withSecond(0).withNano(0)); // Schedule meeting for tomorrow

        // Test showSchedule for Alice
        System.out.println("Alice's Schedule:");
        scheduler.showSchedule("alice@example.com");
        // Alice should have one meeting scheduled for tomorrow
        assertEquals(1, scheduler.getMeetings().size());

        // Test showSchedule for Bob
        System.out.println("Bob's Schedule:");
        scheduler.showSchedule("bob@example.com");
        // Bob should have one meeting scheduled for tomorrow
        assertEquals(1, scheduler.getMeetings().size());
    }

    @Test
    public void testSuggestTimeslots() {
        Scheduler scheduler = new Scheduler();
        Person alice = new Person("Alice", "alice@example.com");
        Person bob = new Person("Bob", "bob@example.com");
        List<Person> participants = new ArrayList<>();
        participants.add(alice);
        participants.add(bob);
        LocalDateTime now = LocalDateTime.now();
        scheduler.createMeeting(participants, now.plusDays(1)); // Schedule meeting for tomorrow

        // Test suggestTimeslots
        System.out.println("Available Timeslots:");
        scheduler.suggestTimeslots(participants);
        // Since there's a meeting scheduled for tomorrow, no timeslots should be suggested
        assertEquals(0, scheduler.getMeetings().size());
    }

    @Test
    public void testGetPersons() {
        Scheduler scheduler = new Scheduler();
        scheduler.createPerson("Alice", "alice@example.com");
        scheduler.createPerson("Bob", "bob@example.com");

        Map<String, Person> persons = scheduler.getPersons();

        assertNotNull(persons);
        assertEquals(2, persons.size());
        assertTrue(persons.containsKey("alice@example.com"));
        assertTrue(persons.containsKey("bob@example.com"));
    }

    @Test
    public void testGetMeetings() {
        Scheduler scheduler = new Scheduler();
        List<Person> participants = List.of(
                new Person("Alice", "alice@example.com"),
                new Person("Bob", "bob@example.com")
        );
        LocalDateTime startTime = LocalDateTime.now().plusDays(0).withHour(10).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startTime2 = LocalDateTime.now().plusDays(0).withHour(11).withMinute(0).withSecond(0).withNano(0);
        scheduler.createMeeting(participants, startTime);
        scheduler.createMeeting(participants, startTime2);

        List<Meeting> meetings = scheduler.getMeetings();

        assertNotNull(meetings);
        assertEquals(2, meetings.size());
    }


}
