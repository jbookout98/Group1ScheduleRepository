package org.example;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository {

    // Custom method to get upcoming events (or courses) within the specified time interval
    List<Course> getUpcomingEvents(LocalDateTime now, int intervalInMinutes);
}
