package com.workshop17.weatherApp.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// class to hold weather properties (to send to thymeleaf)
public class Weather {
    
    private String city;
    private String description; // weather => description
    private String icon; 
    private String temp;
    private String humidity;
    private String todayTime;

    private String sunrise;
    private String sunset;

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getTodayTime() {
        return todayTime;
    }
    public void setTodayTime(String todayTime) {
        
        this.todayTime = todayTime;
    }
    public String getSunrise() {
        return sunrise;
    }
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    public String getSunset() {
        return sunset;
    }
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
    public String getHumidity() {
        return humidity;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    
    public static Weather createWeather(String json) throws IOException {
        Weather w = new Weather();

        // convert json string to byte stream
        try (InputStream is = new ByteArrayInputStream(json.getBytes()) ) {
            // byte stream -> json reader -> json object
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            
            String city = o.get("name").toString();
            // get weather => description & icon
            JsonObject wArrayObj = o.getJsonArray("weather").getJsonObject(0);
            String desc = wArrayObj.get("description").toString();
            String icon = "http://openweathermap.org/img/wn/" + wArrayObj.getString("icon") + "@4x.png";
            
            // get temperature
            JsonObject mainObj = (JsonObject) o.get("main");
            String temp = mainObj.get("temp").toString();
            String humidity = mainObj.get("humidity").toString();
            
            // get timing (current, sunrise, sunset)
            String time = o.get("dt").toString();
            // w.setTodayTime(time);
            // JsonObject sysObj = o.getJsonObject("sys");
            // long sunrise = sysObj.getLong("sunrise");
            // String sunset = sysObj.getLong("sunset");

            // set members
            w.setCity(city);
            w.setDescription(desc);
            w.setIcon(icon);
            w.setTemp(temp);
            w.setHumidity(humidity);
            w.setTodayTime(time);
            // System.out.println("City: " + city);
            // System.out.println("Description: " + desc);
            System.out.println("Icon: " + icon);
            // System.out.println("Temperature: " + temp);
            // System.out.println("Humidity: " + humidity);
            // System.out.println("Time: " + time);

            // System.out.println("Sunrise time: " + sunrise);
            // System.out.println("Sunset time: " + sunset);
            // System.out.println("Current time: " + w.getTodayTime());
            
        }
        return w;
    }


}
