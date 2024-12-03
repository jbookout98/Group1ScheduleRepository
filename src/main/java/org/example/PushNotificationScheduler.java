package org.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PushNotificationScheduler {

    private final CSVService csvService;
    private final PushNotificationService pushNotificationService;

    public PushNotificationScheduler(CSVService csvService, PushNotificationService pushNotificationService) {
        this.csvService = csvService;
        this.pushNotificationService = pushNotificationService;
    }

    @Scheduled(fixedRate = 60000)  // Check every minute
    public void checkAndSendPushNotifications() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> events = csvService.getUpcomingEvents(now, 60); // Example interval: 60 minutes

        for (Event event : events) {
            if (event.isNotifyByPush()) {
                pushNotificationService.sendPushNotification(
                        "Event Reminder",
                        "Your event '" + event.getName() + "' is approaching!",
                        event.getUserId() // Assuming userId is a Firebase token
                );
            }
        }
    }
    
}
