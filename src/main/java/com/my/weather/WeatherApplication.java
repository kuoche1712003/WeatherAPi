package com.my.weather;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.my.weather.repository.CwbOpenDataRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
@Controller
@EnableScheduling
public class WeatherApplication {
        
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }
    @Autowired
    CwbOpenDataRepository repo;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    
    @RequestMapping(value="/")
    public String index() {
        
        return "index";
    }

}
