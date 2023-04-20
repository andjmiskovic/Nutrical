package com.example.diplomski;

import com.example.diplomski.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DiplomskiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomskiApplication.class, args);
    }

}
