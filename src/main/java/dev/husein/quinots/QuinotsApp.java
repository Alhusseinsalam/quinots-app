package dev.husein.quinots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
public class QuinotsApp {

    public static void main(String[] args) {
        SpringApplication.run(QuinotsApp.class, args);
    }

}
