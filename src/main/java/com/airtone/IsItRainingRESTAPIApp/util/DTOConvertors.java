package com.airtone.IsItRainingRESTAPIApp.util;

import com.airtone.IsItRainingRESTAPIApp.dto.MeasurementDTO;
import com.airtone.IsItRainingRESTAPIApp.dto.SensorDTO;
import com.airtone.IsItRainingRESTAPIApp.models.Measurement;
import com.airtone.IsItRainingRESTAPIApp.models.Sensor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DTOConvertors {
    private final ModelMapper modelMapper;

    public DTOConvertors(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    public Map<String, String> convertToSensorDTO(Sensor sensor) {
        Map<String, String> sensorMap = new HashMap<>();
        sensorMap.put("name", sensor.getName());
        return sensorMap;
    }


    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }


    public Map<String, String> convertToMeasurementDTO(Measurement measurement) {

        Map<String, String> measurementMap = new HashMap<>();
        measurementMap.put("value", Double.toString(measurement.getValue()));
        measurementMap.put("raining", Boolean.toString(measurement.getRaining()));
        measurementMap.put("sensor", measurement.getSensor().toString());
        return measurementMap;
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = new Measurement();
        measurement.setValue(Double.parseDouble(measurementDTO.getValue()));
        measurement.setRaining(Boolean.parseBoolean(measurementDTO.getRaining()));
        measurement.setSensor(measurementDTO.getSensor());


        return measurement;
    }
}
