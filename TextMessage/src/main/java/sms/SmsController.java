package sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.rest.api.v2010.account.Message;

@RestController
public class SmsController {

    @Autowired
    SmsService smsService;

    @RequestMapping("/send")
    public String send() {
        return "Send a message with a POST request!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    public Message send(
            @RequestParam(value="to") String to,
            @RequestBody String message) {
        return smsService.send(to, message);
    }
}
