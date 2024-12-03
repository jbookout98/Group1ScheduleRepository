package org.example;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    private static final String ACCOUNT_SID = "your_account_sid";
    private static final String AUTH_TOKEN = "your_auth_token";

    public SMSService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSMS(String to, String body) {
        Message.creator(new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber("your_twilio_phone_number"),
                body).create();
    }
}

