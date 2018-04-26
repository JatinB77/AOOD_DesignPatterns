package SMS;

import com.twilio.rest.api.v2010.account.Message;

public interface SmsService {

    Message send(String to, String messageBody);
}
