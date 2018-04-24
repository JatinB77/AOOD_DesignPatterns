package sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Map;

public class SmsServiceTwilio implements SmsService {

    private PhoneNumber senderNumber;

    public SmsServiceTwilio() {
        Map<String, String> env = System.getenv();

        senderNumber = new PhoneNumber(env.get("TWILIO_PHONE"));

        String accountSid = env.get("TWILIO_SID");
        String authToken = env.get("TWILIO_TOKEN");

        Twilio.init(accountSid, authToken);
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
