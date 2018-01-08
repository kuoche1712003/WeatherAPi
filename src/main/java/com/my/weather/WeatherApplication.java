package com.my.weather;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@SpringBootApplication
public class WeatherApplication {
    
    private static final Logger log = LoggerFactory.getLogger(WeatherApplication.class); 
    
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
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
            //建立ObjectMapper用來將Object轉換成json string
            ObjectMapper jsonmapper = new ObjectMapper();
            //用Xmlmapper轉換xml成object
            CwbOpenData data = mapper.readValue(weatherFile, CwbOpenData.class);
            //寫log測試是否成功轉換
            log.info(data.getLocation()[0].getLat());
            //用標準輸出輸出json String 測試是否成功轉換
            System.out.println(jsonmapper.writerWithDefaultPrettyPrinter().writeValueAsString(data.getLocation()[0]));
        };
    }

}
