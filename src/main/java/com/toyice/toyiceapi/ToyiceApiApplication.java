package com.toyice.toyiceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ToyiceApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ToyiceApiApplication.class, args);
  }

}
