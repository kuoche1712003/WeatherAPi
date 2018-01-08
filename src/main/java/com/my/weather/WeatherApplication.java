package com.my.weather;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.my.weather.domain.CwbOpenData;
import com.my.weather.domain.Location;
import com.my.weather.repository.CwbOpenDataRepository;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class WeatherApplication {
    
    private static final Logger log = LoggerFactory.getLogger(WeatherApplication.class); 
    
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }
    @Autowired
    CwbOpenDataRepository repo;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    
    @Bean
    CommandLineRunner myMethod(RestTemplate restTemplate) {
        return args -> {
            
            String xml = restTemplate.getForObject("http://opendata.cwb.gov.tw/opendataapi?dataid=O-A0001-001&authorizationkey=CWB-849CAA88-0A70-41E9-B763-7B1C95EC0B3D",String.class);
            
            //建立jackson的XmlMapper用來將Xml file轉換成java object
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper mapper = new XmlMapper(module);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //建立ObjectMapper用來將Object轉換成json string
            ObjectMapper jsonmapper = new ObjectMapper();
            
            CwbOpenData data = mapper.readValue(xml, CwbOpenData.class);
            repo.save(data);
        };
    }

}
