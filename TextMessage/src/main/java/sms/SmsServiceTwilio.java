package sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SmsServiceTwilio implements SmsService {

    private PhoneNumber senderNumber;

    public SmsServiceTwilio() {
        /*
        Map<String, String> env = System.getenv();

        senderNumber = new PhoneNumber(env.get("TWILIO_PHONE"));

        String accountSid = env.get("TWILIO_SID");
        String authToken = env.get("TWILIO_TOKEN");
        */
        HashMap<String, String> authVariables = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("twilio_auth.conf"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.trim().split(":");
                authVariables.put(split[0], split[1]);
            }
        } catch (IOException ex) {

        }

        senderNumber = new PhoneNumber(authVariables.get("PHONE_NUMBER"));
        Twilio.init(authVariables.get("ACCOUNT_SID"), authVariables.get("AUTH_TOKEN"));
    }

    @Override
    public Message send(String to, String messageBody) {
        return Message.creator(
            new PhoneNumber(to),
            senderNumber,
            messageBody
        ).create();
    }
}
