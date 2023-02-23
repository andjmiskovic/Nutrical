package com.example.diplomski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DiplomskiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomskiApplication.class, args);
    }

}
