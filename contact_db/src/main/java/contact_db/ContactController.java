package contact_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.twilio.rest.api.v2010.account.Message;

import java.util.Dictionary;
import java.util.HashMap;

@RestController
public class ContactController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ContactService contactService;

    @RequestMapping(method = RequestMethod.POST, value = "/new_contact")
    public String newContact(
         @RequestParam(value="name") String name,
         @RequestParam(value="number") String number
    ) {
        contactService.addContact(name, number);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove_contact")
    public String removeContact(
            @RequestParam(value="name") String name
    ) {
        contactService.removeContact(name);
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_number")
    public String getNumber(
            @RequestParam(value="name") String name
    ) {
        return contactService.getNumber(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send_message")
    public String sendMessage(
            @RequestParam(value="name") String name,
            @RequestBody String message
    )
    {
        String number = contactService.getNumber(name);
        RestTemplate template = new RestTemplate();
        HashMap<String,String> variables = new HashMap<>();
        String response =
            template.postForObject(
                "http://sms:8080/send?to=" + number,
                message,
                String.class,
                variables
            );
        return "Message sent!";
    }

    @RequestMapping("/message")
    public String testMessage() {
        RestTemplate template = new RestTemplate();
        String response =
            template.getForObject("http://sms:8080/send", String.class,
                new HashMap<String, String>());
        return response;
    }
}
