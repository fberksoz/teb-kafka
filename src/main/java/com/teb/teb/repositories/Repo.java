package com.teb.teb.repositories;

import com.teb.teb.objects.City;
import com.teb.teb.objects.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Repo {

    @Autowired
    private ILogRepository logRepository;
    @Autowired
    private ICityRepository cityRepository;

    public void saveLog(Log log) {
        City city = cityRepository.getCityByName(log.getCity().getName());
        if (city == null) {
            city = cityRepository.save(log.getCity());
        }
        log.setCity(city);
        logRepository.save(log);
    }

}
