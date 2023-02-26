package ru.levshin.trial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TrialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrialApplication.class, args);
    }

}
