package sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
            @RequestParam(value="number") String number,
            @RequestBody String message) {
        return "Message status: " + smsService.send(number, message).getStatus();
    }
}
