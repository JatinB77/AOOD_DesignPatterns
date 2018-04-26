package ContactDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;

@RestController
public class ContactController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.POST, value = "/new_contact")
    public String newContact(
            @RequestParam(value="name") String name,
            @RequestParam(value="number") String number
    ) {
        jdbcTemplate.execute(String.format(
                "INSERT INTO phone_numbers (name,number) VALUES ('%s', '%s')",
                name, number
        ));
        return "OK";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/remove_contact")
    public String removeContact(
            @RequestParam(value="name") String name
    ) {
        jdbcTemplate.execute(String.format(
                "DELETE FROM phone_numbers WHERE name = %s", name
        ));
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get_number")
    public String getNumber(
            @RequestParam(value="name") String name
    ) {
        String phone = (String) jdbcTemplate.queryForObject(String.format(
                "SELECT number FROM phone_numbers WHERE name = '%s'",
                name
        ), new Object[]{}, String.class);
        return phone;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send_message")
    public String sendMessage(
            @RequestParam(value="name") String name
    )
    {
        String number = getNumber(name);
        RestTemplate template = new RestTemplate();
        HashMap<String,String> variables = new HashMap<>();
        variables.put("name", name);
        template.postForObject("172.18.0.3/send/", null, Void.class, variables);
        return "OK";
    }
}