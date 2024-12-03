package org.example;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    public void sendPushNotification(String title, String message, String userId) {
        // Build the push notification message
        Message notificationMessage = Message.builder()
                .setNotification(new Notification(title, message))
                .setToken(userId)  // userId is assumed to be the Firebase device token
                .build();

        try {
            // Send the message via Firebase Cloud Messaging
            FirebaseMessaging.getInstance().send(notificationMessage);
            System.out.println("Push notification sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending push notification: " + e.getMessage());
        }
    }
}
