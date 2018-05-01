package contact_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ContactServiceDB implements ContactService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addContact(String name, String number) {
        String query = String.format(
            "INSERT INTO phone_numbers (name,number) VALUES ('%s', '%s')",
            name, number
        );

        jdbcTemplate.execute(query);
    }

    @Override
    public void removeContact(String name) {
        String query = String.format(
            "DELETE FROM phone_numbers WHERE name = '%s'", name
        );

        jdbcTemplate.execute(query);
    }

    @Override
    public String getNumber(String name) {
        String query = String.format(
            "SELECT number FROM phone_numbers WHERE name = '%s'", name
        );

        return jdbcTemplate.queryForObject(
            query,
            new Object[]{},
            String.class
        );
    }
}
