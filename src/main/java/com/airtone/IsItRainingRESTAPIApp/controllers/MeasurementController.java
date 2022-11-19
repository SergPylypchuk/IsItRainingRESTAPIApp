package com.airtone.IsItRainingRESTAPIApp.controllers;

import com.airtone.IsItRainingRESTAPIApp.dto.MeasurementBatchDTO;
import com.airtone.IsItRainingRESTAPIApp.dto.MeasurementDTO;
import com.airtone.IsItRainingRESTAPIApp.util.services.MeasurementService;
import com.airtone.IsItRainingRESTAPIApp.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ErrorChecker errorChecker;
    private final DTOConvertors dtoConvertors;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ErrorChecker errorChecker, DTOConvertors dtoConvertors) {
        this.measurementService = measurementService;
        this.errorChecker = errorChecker;
        this.dtoConvertors = dtoConvertors;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addBatchMeasurement(@RequestBody @Valid MeasurementBatchDTO measurementBatchDTO, BindingResult bindingResult) {


        for(MeasurementDTO mbd : measurementBatchDTO.getMeasurement()) {

            errorChecker.fieldsChecker(bindingResult);
            errorChecker.isRegistered(mbd.getSensor().getName());

            measurementService.addMeasurement(dtoConvertors.convertToMeasurement(mbd));

        }
        return ResponseEntity.ok(HttpStatus.OK);
    }


@GetMapping()
public HttpEntity<Map<String, List<Map<String, String>>>> findAll() {
    Map<String, List<Map<String, String>>> allMeasurementMapList = new HashMap<>();
    List<Map<String, String>> allMeasurementString = measurementService.findAll()
            .stream()
            .map(m -> dtoConvertors.convertToMeasurementDTO(m))
            .collect(Collectors.toList());

        allMeasurementMapList.put("measurement",allMeasurementString);

    return new HttpEntity<Map<String, List<Map<String, String>>>>(allMeasurementMapList);
}


    @GetMapping("/rainyDaysCount")
    public String rainyDaysCount() {
        return measurementService.rainyDaysCount();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clearData() {
        measurementService.clearAllMeasurements();
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
    private ResponseEntity<SensorErrorResponse> handleException(WrongDataException e) {
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
