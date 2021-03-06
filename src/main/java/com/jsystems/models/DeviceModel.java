package com.jsystems.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceModel {

    public String produce;

    @JsonProperty(value = "screen.size", required = true)
    public String screen_size;

    @Override
    public String toString() {
        return "DeviceModel{" +
                "producec='" + produce + '\'' +
                ", screen_size='" + screen_size + '\'' +
                '}';
    }
}
