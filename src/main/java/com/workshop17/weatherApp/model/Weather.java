package com.workshop17.weatherApp.model;

// class to hold weather properties (to send to thymeleaf)
public class Weather {
    
    private String description; // weather => description
    private String icon; 
    private String temp;
    private String todayTime;
    private String sunrise;
    private String sunset;

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
    
    public static Weather createWeather(String json) {


        return null;
    }


}
