package com.airtone.IsItRainingRESTAPIApp.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeasurementBatchDTO {
    List<MeasurementDTO> Measurement = new ArrayList<>();

    public List<MeasurementDTO> getMeasurement() {
        return Measurement;
    }

    public void setMeasurement(List<MeasurementDTO> measurement) {
        Measurement = measurement;
    }
}
