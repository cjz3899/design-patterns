package com.junzhecai.observer.WeatherStation;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
