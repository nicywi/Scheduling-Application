package com.nkcdev.scheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// SchedulingApp class containing main functionalities
public class SchedulingApp {
    private Map<String, Person> persons; // Mapping of email to Person object
    private List<Meeting> meetings; // List to store scheduled meetings

    public SchedulingApp() {
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

    private void createMeeting(List<Person> participants, LocalDateTime startTime) {
        if (startTime.getMinute() != 0 || startTime.getSecond() != 0) {
            System.out.println("Meeting can only start at the hour mark.");
            return;
        }

        meetings.add(new Meeting(participants, startTime));
        System.out.println("Meeting scheduled at " + startTime.toString());
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
                System.out.println("Meeting at " + meeting.getStartTime().toString());
            }
        }
    }
    // Suggest available timeslots for a meeting given a group of persons
    public void suggestTimeslots(List<Person> participants) {
        System.out.println("Available timeslots for meeting:");
        for (int hour = 0; hour <= 23; hour++) {
            LocalDateTime startTime = LocalDateTime.now().withHour(hour).withMinute(0).withSecond(0);
            boolean available = true;
            for (Meeting meeting : meetings) {
                if (meeting.getStartTime().isEqual(startTime) || // Meeting starts at this hour
                        (meeting.getStartTime().isAfter(startTime) && // Meeting starts after this hour
                                meeting.getStartTime().isBefore(startTime.plusHours(1))) || // but before next hour
                        (meeting.getStartTime().plusHours(1).isAfter(startTime) && // Meeting ends after this hour
                                meeting.getStartTime().plusHours(1).isBefore(startTime.plusHours(1)))) { // but before next hour
                    available = false;
                    break;
                }
            }
            if (available) {
                System.out.println(startTime.toString());
            }
        }
    }

    public static void main( String[] args ) {
        SchedulingApp app = new SchedulingApp();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Scheduling Application Demo!");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Person");
            System.out.println("2. Schedule Meeting");
            System.out.println("3. Show Schedule for Person");
            System.out.println("4. Suggest Timeslots for Meeting");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter person's name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter person's email:");
                    String email = scanner.nextLine();
                    app.createPerson(name, email);
                    break;
                case 2:
                    System.out.println("Enter number of participants:");
                    int numParticipants = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    List<Person> participants = new ArrayList<>();
                    for (int i = 0; i < numParticipants; i++) {
                        System.out.println("Enter participant " + (i + 1) + "'s email:");
                        String participantEmail = scanner.nextLine();
                        participants.add(new Person("", participantEmail));
                        app.createPerson("", participantEmail);
                    }

                    System.out.println("Enter meeting start date and time (yyyy-MM-dd HH:mm):");
                    String dateTimeStr = scanner.nextLine().trim(); // Trim the input string
                    // Check if the input string has the correct format
                    if (!dateTimeStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
                        System.out.println("Invalid format. Please enter date and time in yyyy-MM-dd HH:mm format.");
                        return;
                    }
                    LocalDateTime startTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    app.createMeeting(participants, startTime);
//                    LocalDateTime startTime = LocalDateTime.parse(dateTimeStr);
                    break;
                case 3:
                    System.out.println("Enter person's email:");
                    String personEmail = scanner.nextLine();
                    app.showSchedule(personEmail);
                    break;
                case 4:
                    System.out.println("Enter number of participants:");
                    int numParticipantsForSuggestion = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    List<Person> participantsForSuggestion = new ArrayList<>();
                    for (int i = 0; i < numParticipantsForSuggestion; i++) {
                        System.out.println("Enter participant " + (i + 1) + "'s email:");
                        String participantEmail = scanner.nextLine();
                        participantsForSuggestion.add(new Person("", participantEmail));
                    }

                    app.suggestTimeslots(participantsForSuggestion);
                    break;
                case 5:
                    System.out.println("Exiting the Scheduling Application Demo. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }


}

