package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CSVService {

    public List<Event> getUpcomingEvents(LocalDateTime now, int intervalInMinutes) {
        // Get list of courses from the CSV file
        List<Course> courses = CSVCourseReader.readCoursesFromCSV("courses.csv");
        LocalDateTime nowPlusInterval = now.plusMinutes(intervalInMinutes);

        // Convert the list of courses to events and filter based on start time
        return courses.stream()
                .map(course -> {
                    Event event = convertCourseToEvent(course);
                    return event;
                })
                .filter(event -> event.getStartTime().isAfter(now) && event.getStartTime().isBefore(nowPlusInterval))
                .collect(Collectors.toList());
    }

    private Event convertCourseToEvent(Course course) {
        // Convert Course object to Event object
        Event event = new Event();
        event.setName(course.getSubject());  // Assuming Subject is the event name
        event.setStartTime(parseTime(course.getStartTime()));  // Parse start time
        // Set other Event fields based on Course fields as necessary
        return event;
    }

    private LocalDateTime parseTime(String timeStr) {
        // Assuming the time format is 12-hour (e.g., 2:00 PM, 11:00 AM)
        // Parse the time into LocalDateTime format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return LocalDateTime.parse(timeStr, formatter);
    }
}
