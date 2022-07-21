package com.toyice.toyiceapi.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private static final String INFO_TITLE = "Toyice";
  private static final String INFO_VERSION = "0.0.1";
  private static final String INFO_DESCRIPTION = "Toyice API Specification";

  @Bean
  public Docket api(){
    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    docket.apiInfo(this.createApiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/**"))
        .build();

    int tagOrd = 0;
    docket.tags(
        new Tag("Toy", "토이 API", ++tagOrd)
    );

    return docket;
  }

  private ApiInfo createApiInfo(){
    return new ApiInfoBuilder()
        .title(INFO_TITLE)
        .version(INFO_VERSION)
        .description(INFO_DESCRIPTION)
        .build();
  }

  @Bean
  public UiConfiguration uiConfiguration(){
    return UiConfigurationBuilder.builder()
        .defaultModelRendering(ModelRendering.MODEL)
        .build();
  }

}
