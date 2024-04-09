package com.nkcdev.scheduling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// SchedulingApp class containing main functionalities
public class SchedulingApp {
    private List<Person> persons; // List to store persons
    private List<Meeting> meetings; // List to store scheduled meetings

    public SchedulingApp() {
        persons = new ArrayList<>();
        meetings = new ArrayList<>();
    }

    // Create a new person with name and email
    public void createPerson(String name, String email) {
        // Check if person with the same email already exists
        if (findPersonByEmail(email) != null) {
            System.out.println("Email already exists.");
            return;
        }
        // Create and add the person to the list
        persons.add(new Person(name, email));
        System.out.println("Person created: " + name);
    }

    // Find a person by email
    private Person findPersonByEmail(String email) {
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null; // Person not found
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
                    }

                    System.out.println("Enter meeting start date and time (yyyy-MM-dd HH:mm):");
                    String dateTimeStr = scanner.nextLine();
                    LocalDateTime startTime = LocalDateTime.parse(dateTimeStr);
//                    app.createMeeting(participants, startTime);
                    break;
                case 3:
                    System.out.println("Enter person's email:");
                    String personEmail = scanner.nextLine();
//                    app.showSchedule(personEmail);
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

//                    app.suggestTimeslots(participantsForSuggestion);
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

