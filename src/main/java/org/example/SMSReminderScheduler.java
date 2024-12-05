package org.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SMSReminderScheduler {

    private final EventRepository eventRepository;
    private final SMSService smsService;

    public SMSReminderScheduler(EventRepository eventRepository, SMSService smsService) {
        this.eventRepository = eventRepository;
        this.smsService = smsService;
    }

    @Scheduled(fixedRate = 60000)  // Check every minute
    public void checkAndSendSMSReminders() {
        // Get the current time
        LocalDateTime now = LocalDateTime.now();

        // Get upcoming courses (List<Course>) within the next 60 minutes
        List<Course> courses = eventRepository.getUpcomingEvents(now, 60);  // This will return List<Course>

        // Loop through each course and send SMS if needed
        for (Course course : courses) {
            if (course.isNotifyBySMS()) {  // If the course has SMS notification enabled
                smsService.sendSMS(
                        course.getUserPhoneNumber(),  // Send SMS to the user
                        "Reminder: Your event '" + course.getSubject() + "' starts at " + course.getStartTime() + "."
                );
            }
        }
    }
}
