package com.junzhecai.observer.WeatherStation;

public interface Observer {
    void update(float temp, float humidity, float pressure);
}
