package contact_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Dictionary;

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
}
