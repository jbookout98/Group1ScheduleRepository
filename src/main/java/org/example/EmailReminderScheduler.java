package org.example;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailReminderScheduler {

    private final EventRepository eventRepository;
    private final NotificationService notificationService;

    public EmailReminderScheduler(EventRepository eventRepository, NotificationService notificationService) {
        this.eventRepository = eventRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 60000) // Check every minute
    public void checkAndSendEmailReminders() {
        List<Event> events = eventRepository.findUpcomingEvents(LocalDateTime.now());
        for (Event event : events) {
            if (event.isNotifyByEmail()) {
                notificationService.sendEmail(
                    event.getUserEmail(),
                    "Event Reminder: " + event.getName(),
                    "Your event '" + event.getName() + "' starts at " + event.getStartTime() + "."
                );
            }
        }
    }
}

