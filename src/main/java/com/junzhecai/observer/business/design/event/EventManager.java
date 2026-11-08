package com.junzhecai.observer.business.design.event;

import com.junzhecai.observer.business.design.LotteryResult;
import com.junzhecai.observer.business.design.event.listener.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EventManager {

    Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            listeners.put(operation, new ArrayList<>());
        }
    }

    public enum EventType {
        MQ, Message
    }

    /**
     * 订阅
     */
    public void subscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * 取消订阅
     */
    public void unsubscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    /**
     * 通知
     */
    public void notify(Enum<EventType> eventType, LotteryResult result) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.doEvent(result);
        }
    }
}
