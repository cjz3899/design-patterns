package com.junzhecai.observer.WeatherStation;

import java.nio.file.Path;
import java.util.Optional;

public class WeatherStation {

    private static final Path DATA = Path.of(
            "src/main/java/com/junzhecai/observer/WeatherStation/weather.jsonl"
    );
    private static final long POLL_INTERVAL_MS = 5000;

    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);

        JsonWeatherReader reader = new JsonWeatherReader(DATA);
        String lastTime = null;

        while (!Thread.currentThread().isInterrupted()) {
            Optional<WeatherReading> latest = reader.readLatest();
            if (latest.isPresent()) {
                WeatherReading reading = latest.get();
                if (!reading.time().equals(lastTime)) {
                    lastTime = reading.time();
                    weatherData.setMeasurements(
                            reading.temperature(),
                            reading.humidity(),
                            reading.pressure()
                    );
                }
            }
            Thread.sleep(POLL_INTERVAL_MS);
        }
    }
}
