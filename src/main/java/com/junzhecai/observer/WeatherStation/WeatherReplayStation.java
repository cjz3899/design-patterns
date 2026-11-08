package com.junzhecai.observer.WeatherStation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

/**
 * 用历史快照驱动 Subject，把 10 天的读数压缩到几十秒回放，观察通知链的变化。
 */
public class WeatherReplayStation {

    private static final Logger log = LoggerFactory.getLogger(WeatherReplayStation.class);

    private static final Path DATA = Path.of(
            "src/main/java/com/junzhecai/observer/WeatherStation/weather_history.jsonl"
    );
    private static final long REPLAY_INTERVAL_MS = 200;

    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);

        List<WeatherReading> readings = new JsonWeatherReader(DATA).readAll();
        if (readings.isEmpty()) {
            log.info("没有数据，先跑 fetch_history.py");
            return;
        }
        log.info("回放 {} 条读数，{} → {}", readings.size(), readings.getFirst().time(), readings.getLast().time());

        // 回放不去重：每条是一个不同小时的读数，相邻小时数值相同也应各自通知一次
        for (WeatherReading reading : readings) {
            weatherData.setMeasurements(
                    reading.temperature(),
                    reading.humidity(),
                    reading.pressure()
            );
            Thread.sleep(REPLAY_INTERVAL_MS);
        }
    }
}