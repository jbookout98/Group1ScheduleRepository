package org.example;


@Service
public class SMSReminderScheduler {

    private final EventRepository eventRepository;
    private final SMSService smsService;

    public SMSReminderScheduler(EventRepository eventRepository, SMSService smsService) {
        this.eventRepository = eventRepository;
        this.smsService = smsService;
    }

    @Scheduled(fixedRate = 60000) // Check every minute
    public void checkAndSendSMSReminders() {
        List<Event> events = eventRepository.findUpcomingEvents(LocalDateTime.now());
        for (Event event : events) {
            if (event.isNotifyBySMS()) {
                smsService.sendSMS(
                        event.getUserPhoneNumber(),
                        "Reminder: Your event '" + event.getName() + "' starts at " + event.getStartTime() + ".");
            }
        }
    }
}
