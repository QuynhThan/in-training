package com.ms.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.ms.training.*"}
                        , exclude = {SecurityAutoConfiguration.class})
public class MsTrainingMaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsTrainingMaintenanceApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer configure() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry reg) {
//                reg.addMapping("/**").allowedOrigins("*");
//            }
//        };
//    }
}
