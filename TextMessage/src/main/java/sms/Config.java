package sms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public SmsService getSmsService() {
        return new SmsServiceTwilio();
    }
}
