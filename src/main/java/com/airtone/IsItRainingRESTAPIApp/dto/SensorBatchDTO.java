package com.airtone.IsItRainingRESTAPIApp.dto;

import java.util.ArrayList;
import java.util.List;

public class SensorBatchDTO {
    private List<SensorDTO> sensor = new ArrayList<>();

    public List<SensorDTO> getSensor() {
        return sensor;
    }

    public void setSensor(List<SensorDTO> sensor) {
        this.sensor = sensor;
    }
}
