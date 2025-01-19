package com.alura.literatura;

import com.alura.literatura.controller.LiteratureController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(LiteratureController literatureController) {
        return args -> literatureController.showMenu();
    }
}
