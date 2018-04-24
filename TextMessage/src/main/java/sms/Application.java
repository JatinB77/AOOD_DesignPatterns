package sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE phone_numbers IF EXISTS");
        jdbcTemplate.execute(
            "CREATE TABLE phone_numbers(" +
            "id SERIAL, " +
            "name VARCHAR(128), " +
            "number VARCHAR(12))"
        );
        HashMap<String, String> contacts = new HashMap<>();
        contacts.put("johan", "+13025215161");
        jdbcTemplate.execute(String.format(
            "INSERT INTO phone_numbers (name, number) VALUES ('%s', '%s')",
            "johan",
            contacts.get("johan")
        ));
    }
}
