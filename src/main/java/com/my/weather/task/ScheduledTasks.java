package com.my.weather.task;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.my.weather.domain.CwbOpenData;
import com.my.weather.repository.CwbOpenDataRepository;
import com.my.weather.repository.LocationRepository;
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    LocationRepository repo;
    
    @Autowired
    CwbOpenDataRepository datarepo;
    
    
    @Scheduled(fixedRate = 3600000)
    public void updatedata() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        String xml = restTemplate.getForObject("http://opendata.cwb.gov.tw/opendataapi?dataid=O-A0001-001&authorizationkey=CWB-849CAA88-0A70-41E9-B763-7B1C95EC0B3D",String.class);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            CwbOpenData data = mapper.readValue(xml, CwbOpenData.class);
            Long cont = repo.countByLocationNameAndTimeObsTime(data.getLocation().get(0).getLocationName(),data.getLocation().get(0).getTime().getObsTime());
            if(cont>0){
                log.info("資料是最新的"+dateFormat.format(new Date()));
            }else {
                datarepo.save(data);
                log.info("資料更新完畢"+dateFormat.format(new Date()));
            }
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
}
