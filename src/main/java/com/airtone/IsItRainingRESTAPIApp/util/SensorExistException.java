package com.airtone.IsItRainingRESTAPIApp.util;

public class SensorExistException extends RuntimeException{
    public SensorExistException(String msg) {
        super(msg);
    }
}
