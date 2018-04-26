package SMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    SmsService smsService;

    @RequestMapping("/send")
    public String send() {
        return "Send a message with a POST request!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    public String send(
            @RequestParam(value = "number") String number,
            @RequestBody String message) {
        return "Message status: " + smsService.send(number, message).getStatus();
    }
}