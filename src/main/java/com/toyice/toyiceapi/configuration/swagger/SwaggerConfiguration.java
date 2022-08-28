package com.toyice.toyiceapi.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI openAPI(){
    Info info = new Info()
        .title("Toyice")
        .version("0.0.1")
        .description("Toyice API Specification");
    return new OpenAPI()
        .components(new Components())
        .info(info);
  }

}
