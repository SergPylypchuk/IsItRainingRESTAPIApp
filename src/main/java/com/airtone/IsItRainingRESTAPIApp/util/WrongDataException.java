package com.airtone.IsItRainingRESTAPIApp.util;

public class WrongDataException extends RuntimeException{
    public WrongDataException(String msg) {
        super(msg);
    }
}
