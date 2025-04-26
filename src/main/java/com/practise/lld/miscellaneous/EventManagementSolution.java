package com.practise.lld.miscellaneous;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

interface IPerson {
    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);
}

interface IEventInfo {
    String getEventName();

    void setEventName(String eventName);

    LocalDate getEventDate();

    void setEventDate(LocalDate eventDate);

    int getCapacity();

    void setCapacity(int capacity);

    boolean isCanceled();

    void setCanceled(boolean canceled);

    List<IPerson> getRegistrations();

    void setRegistrations(List<IPerson> registrations);

    List<IPerson> getAttendees();

    void setAttendees(List<IPerson> attendees);

    void register(IPerson person);

    void attend(IPerson person);
}

interface IEventManager {
    List<IEventInfo> getEvents();

    void setEvents(List<IEventInfo> events);

    void addEvent(IEventInfo eventInfo);

    void register(String eventName, IPerson person);

    void attend(String eventName, IPerson person);

    List<String> getEventCountByYears();

    List<String> getEventRegistrationCountByYears();

    List<String> getEventAttendeesCountByYears();
}
/*
 * Create Person, EventInfo and EventManager classes
 */

class Person implements IPerson {

    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class EventInfo implements IEventInfo {

    private String eventName;
    private int capacity;
    private boolean isCanceled;
    private LocalDate eventDate;

    private List<IPerson> registrations;
    private List<IPerson> attendees;

    public EventInfo(String eventName, LocalDate evDate, int capacity, boolean isCanceled) {
        this.eventDate = evDate;
        this.eventName = eventName;
        this.capacity = capacity;
        this.isCanceled = isCanceled;
        registrations = new ArrayList<>();
        attendees = new ArrayList<>();
    }


    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean isCanceled() {
        return isCanceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.isCanceled = isCanceled;
    }

    @Override
    public List<IPerson> getRegistrations() {
        return registrations;
    }

    @Override
    public void setRegistrations(List<IPerson> registrations) {
        this.registrations = registrations;
    }

    @Override
    public List<IPerson> getAttendees() {
        return attendees;
    }

    @Override
    public void setAttendees(List<IPerson> attendees) {
        this.attendees = attendees;
    }

    @Override
    public void register(IPerson person) {
        if (person != null && !isCanceled && !registrations.contains(person)) {
            registrations.add(person);
        }
    }

    @Override
    public void attend(IPerson person) {
        if (!isCanceled && registrations.contains(person)) {
            attendees.add(person);
        }
    }
}


class EventManager implements IEventManager {

    private List<IEventInfo> eventList;

    public EventManager() {
        this.eventList = new ArrayList<>();
    }

    @Override
    public List<IEventInfo> getEvents() {
        return eventList;
    }

    @Override
    public void setEvents(List<IEventInfo> events) {
        this.eventList = events;
    }

    @Override
    public void addEvent(IEventInfo eventInfo) {
        eventList.add(eventInfo);
    }

    @Override
    public void register(String eventName, IPerson person) {
        for (IEventInfo eventInfo : eventList) {
            if (eventInfo.getEventName().equals(eventName)) {
                eventInfo.register(person);
            }
        }
    }

    @Override
    public void attend(String eventName, IPerson person) {
        for (IEventInfo eventInfo : eventList) {
            if (eventInfo.getEventName().equals(eventName)) {
                eventInfo.attend(person);
            }
        }
    }

    @Override
    public List<String> getEventCountByYears() {
        // Group events by year and count them
        Map<Integer, Long> eventCountByYear = eventList.stream()
                .collect(Collectors.groupingBy(
                        event -> event.getEventDate().getYear(),
                        Collectors.counting()
                ));

        // Convert to list of formatted strings
        return eventCountByYear.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEventRegistrationCountByYears() {
        // Group events by year and sum registrations
        Map<Integer, Long> registrationCountByYear = eventList.stream()
                .collect(Collectors.groupingBy(
                        event -> event.getEventDate().getYear(),
                        Collectors.summingLong(event -> event.getRegistrations().size())
                ));

        // Convert to list of formatted strings
        return registrationCountByYear.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEventAttendeesCountByYears() {
        // Group events by year and sum attendees
        Map<Integer, Long> attendeesCountByYear = eventList.stream()
                .collect(Collectors.groupingBy(
                        event -> event.getEventDate().getYear(),
                        Collectors.summingLong(event -> event.getAttendees().size())
                ));

        // Convert to list of formatted strings
        return attendeesCountByYear.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }
}


public class EventManagementSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.getenv("OUTPUT_PATH"), "UTF-8");

        List<IPerson> persons = new ArrayList<>();
        List<IEventInfo> events = new ArrayList<>();
        EventManager evtM = new EventManager();
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 1; i <= n; i++) {
            String[] a = reader.readLine().trim().split(" ");
            persons.add(new Person(a[0], a[1]));
        }

        int m = Integer.parseInt(reader.readLine().trim());
        for (int i = 1; i <= m; i++) {
            String[] a = reader.readLine().trim().split(" ");
            LocalDate date = LocalDate.of(Integer.parseInt(a[1].split("\\.")[0]), Integer.parseInt(a[1].split("\\.")[1]), Integer.parseInt(a[1].split("\\.")[2]));
            events.add(new EventInfo(a[0], date, Integer.parseInt(a[2]), a[3].equals("1")));
        }

        for (IEventInfo item : events) {
            evtM.addEvent(item);
        }

        int p = Integer.parseInt(reader.readLine().trim());
        for (int i = 1; i <= p; i++) {
            String[] a = reader.readLine().trim().split(" ");
            if (a[0].equals("1")) {
                evtM.register(events.get(Integer.parseInt(a[2])).getEventName(), persons.get(Integer.parseInt(a[1])));
            } else if (a[0].equals("2")) {
                evtM.attend(events.get(Integer.parseInt(a[2])).getEventName(), persons.get(Integer.parseInt(a[1])));
            }
        }
        List<String> yy = evtM.getEventCountByYears();
        List<String> b = evtM.getEventRegistrationCountByYears();
        List<String> c = evtM.getEventAttendeesCountByYears();
        writer.println("Event Count:");
        for (String item : yy) {
            writer.println(item);
        }
        writer.println("Registrations:");
        for (String item : b) {
            writer.println(item);
        }
        writer.println("Attendees:");
        for (String item : c) {
            writer.println(item);
        }

        writer.flush();
        writer.close();
    }
}