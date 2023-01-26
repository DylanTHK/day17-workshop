package com.workshop17.weatherApp.controller;

import java.security.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workshop17.weatherApp.model.Weather;
import com.workshop17.weatherApp.service.WeatherService;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping
public class WeatherController {
    
    @Autowired
    WeatherService weatherSvc;
    
    
    // mapping to GET index page
    @GetMapping(path="/")
    public String getIndex() {
        return "index";
    }

    // 
    @GetMapping(path="/weather")
    public String getWeather(@RequestParam(required=false) String city, 
                @RequestParam(required=false) String units,
                Model model) {
        
        // set default values for inputs
        if ((null == city) || (city.equals(""))) {
            city = "singapore";
        }
        if (null == units) {
            units = "metric";
        }

        System.out.println("City detected: " + city);

        // method to generate URL and make request
        Weather w = weatherSvc.getWeather(city, units);
        
        // add weather object to model
        model.addAttribute("weather", w);

        return "weather";
    }

}
