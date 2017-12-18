package com.cattechnologies.tpg.Model;

/**
 * Created by admin on 11/17/2017.
 */

public class EventBusModel {
    private final String message;

    public EventBusModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
