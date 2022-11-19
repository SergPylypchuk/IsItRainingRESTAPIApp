package com.airtone.IsItRainingRESTAPIApp.util;

import com.airtone.IsItRainingRESTAPIApp.util.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;


@Component
public class ErrorChecker {
    private final SensorService sensorService;

    @Autowired
    public ErrorChecker(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    public void doubleNameChecker(String name) {
        if(sensorService.isExists(name))
            throw new SensorExistException("A sensor with this name already exists");
    }

    public void isRegistered(String sensorName) {
        // Check the Sensor sending its measurement is registered.
        // If not, the measurement will not be saved in db (message: "Unregistered sensor")
        if(!sensorService.isExists(sensorName))
            throw new SensorExistException("Unregistered sensor");
    }

    public void fieldsChecker(BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuilder errorMSG = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMSG.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotRegisteredException(errorMSG.toString());
        }
    }
}
