package edu.nju.stories.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xmc1993 on 16/8/25.
 */
@Configuration //必须存在
@EnableSwagger2 //必须存在
@ComponentScan(basePackages = {"edu.nju.stories.rest"}) //必须存在 扫描的API Controller package name 也可以直接扫描class (basePackageClasses)
public class MySwaggerConfig {
  @Bean
  public Docket customDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .select()
            .apis((input)-> {
                Class<?> declaringClass = input.declaringClass();
                if(declaringClass.isAnnotationPresent(RestController.class)){
                  return true;
                }
                return false;
              }).build();
  }

  private ApiInfo apiInfo() {
    Contact contact = new Contact("邓逸鹏", "", "707969656@qq.com");
    return new ApiInfoBuilder()
            .title("前台API接口")
            .description("前台API接口")
            .contact(contact)
            .version("1.1.0")
            .build();
  }
}
