package com.airtone.IsItRainingRESTAPIApp.util;

public class SensorNotRegisteredException extends RuntimeException {
    public SensorNotRegisteredException(String msg) {
        super(msg);
    }
}
