package contact_db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ContactService getContactService() {
        return new ContactServiceDB();
    }
}
