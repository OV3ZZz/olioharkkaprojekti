package com.example.olioharkkaprojekti;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class WeatherDataRetriever {
    private final String API_KEY = "1f36e209e2b90b9667adb0e9988576d1";
    private final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public WeatherData getWeatherData(String municipality) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode weatherData;

            weatherData = objectMapper.readTree(new URL(String.format(WEATHER_URL, municipality, API_KEY)));

            WeatherData wd = new WeatherData(
                    weatherData.get("name").asText(),
                    weatherData.get("weather").get(0).get("main").asText(),
                    weatherData.get("weather").get(0).get("description").asText(),
                    weatherData.get("main").get("temp").asText(),
                    weatherData.get("wind").get("speed").asText()
            );
            return wd;

        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

}
