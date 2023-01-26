package com.workshop17.weatherApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.workshop17.weatherApp.model.Weather;

@Service
public class WeatherService {
    // base URL
    private static final String OPEN_WEATHER_URL = 
            "https://api.openweathermap.org/data/2.5/weather";
    
    public Weather getWeather(String city, String units) {
        // get api key from environment (requires manual insertion of values - export API_KEY <value>)
        String url = generateURL(city, units);

        // get response body from response entity
        String respBody = getResponseBody(url);

        // create weather object from json body
        Weather w = Weather.createWeather(respBody);

        // edit to return response body
        return w;
    }

    public String generateURL(String city, String units) {
        // get api key from environment (requires manual insertion of values - export API_KEY <value>)
        String API_KEY = System.getenv("OPEN_WEATHER_API_KEY");

        System.out.println("Detected API key: " + API_KEY);

        // add query to url (city, units, apikey) - use UriComponentsBuilder
        String newURL = UriComponentsBuilder
            .fromUriString(OPEN_WEATHER_URL)
            .queryParam("q", city.replaceAll(" ", "+")) // to append all multiple worded cities with +
            .queryParam("units", units)
            .queryParam("appid", API_KEY)
            .toUriString();

        return newURL;
    }

    public String getResponseBody(String url) {
        // new rest template
        RestTemplate template = new RestTemplate();
        System.out.println("RestTemplate: " + template);
        // obtain entity by doing get from specific url
        ResponseEntity<String> resp = template.getForEntity(url, String.class);
        String body = resp.getBody();
        System.out.println("Response body: " + body);

        return body;
    }
}
