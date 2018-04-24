package sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    SmsService smsService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/send")
    public String send() {
        return "Send a message with a POST request!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    public String send(
            @RequestParam(value="contact") String contact,
            @RequestBody String message) {
        String phone = (String) jdbcTemplate.queryForObject(String.format(
            "SELECT number FROM phone_numbers WHERE name = '%s'",
            contact
        ), new Object[]{}, String.class);
        return "Message status: " + smsService.send(phone, message).getStatus();
    }
}
