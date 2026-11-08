package com.junzhecai.observer.WeatherStation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private static final Logger log = LoggerFactory.getLogger(CurrentConditionsDisplay.class);

    private float temperature;
    private float humidity;
    private float pressure;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        log.info("Current conditions: {} °C, humidity {}%, pressure {} hPa", temperature, humidity, pressure);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }
}
