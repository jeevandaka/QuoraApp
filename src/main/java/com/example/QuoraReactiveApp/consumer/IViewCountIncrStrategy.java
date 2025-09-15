package com.example.QuoraReactiveApp.consumer;

import com.example.QuoraReactiveApp.event.ViewCountEvent;

public interface IViewCountIncrStrategy {
    public void increaseViewCount();
    public void setViewCountEvent(ViewCountEvent event);
}
