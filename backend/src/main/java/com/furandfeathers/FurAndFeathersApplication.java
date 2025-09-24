package com.furandfeathers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FurAndFeathersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FurAndFeathersApplication.class, args);
    }
}
