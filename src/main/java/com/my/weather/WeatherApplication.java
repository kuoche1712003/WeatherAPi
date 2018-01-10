package com.my.weather;




import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.my.weather.domain.CwbOpenData;
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
    @Bean
    CommandLineRunner myMethod() {
        return args -> {
            //讀放在src/main/resources裡的xml檔案
            File weatherFile = new File(getClass().getClassLoader().getResource("weather.xml").getFile());
            //建立jackson的XmlMapper用來將Xml file轉換成java object
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper mapper = new XmlMapper(module);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //用Xmlmapper轉換xml成object
            CwbOpenData data = mapper.readValue(weatherFile, CwbOpenData.class);
            repo.save(data);
        };
    }
}
