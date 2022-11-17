package com.airtone.IsItRainingRESTAPIApp.controllers;

import com.airtone.IsItRainingRESTAPIApp.dto.SensorBatchDTO;
import com.airtone.IsItRainingRESTAPIApp.dto.SensorDTO;
import com.airtone.IsItRainingRESTAPIApp.services.SensorService;
import com.airtone.IsItRainingRESTAPIApp.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ErrorChecker errorChecker;

    @Autowired
    public SensorController(SensorService sensorService, ErrorChecker errorChecker) {
        this.sensorService = sensorService;
        this.errorChecker = errorChecker;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> regSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        errorChecker.doubleNameChecker(sensorDTO.getName());
        errorChecker.fieldsChecker(bindingResult);
        sensorService.save(sensorDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/registrationBatch")
    public ResponseEntity<HttpStatus> regBatchSensor(@RequestBody @Valid SensorBatchDTO sensorBatchDTO, BindingResult bindingResult) {

        for(SensorDTO sensor : sensorBatchDTO.getSensor()) {
            errorChecker.doubleNameChecker(sensor.getName());
            errorChecker.fieldsChecker(bindingResult);
            sensorService.save(sensor);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clearData() {
        sensorService.clearAllSensors();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorExistException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotRegisteredException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(NullPointerException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
