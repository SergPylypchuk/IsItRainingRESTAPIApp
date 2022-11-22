package com.airtone.IsItRainingRESTAPIApp.services;

import com.airtone.IsItRainingRESTAPIApp.dto.SensorDTO;
import com.airtone.IsItRainingRESTAPIApp.models.Sensor;
import com.airtone.IsItRainingRESTAPIApp.repositories.SensorRepository;
import com.airtone.IsItRainingRESTAPIApp.util.DTOConvertors;
import com.airtone.IsItRainingRESTAPIApp.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    private final DTOConvertors dtoConvertors;
    private final MeasurementService measurementService;


    @Autowired
    public SensorService(SensorRepository sensorRepository, DTOConvertors dtoConvertors, MeasurementService measurementService) {
        this.sensorRepository = sensorRepository;
        this.dtoConvertors = dtoConvertors;
        this.measurementService = measurementService;
    }

    public boolean isExists(String name) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(name);
        return foundSensor.isPresent();
    }

    public Sensor findSensorByName(String name) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(name);
        return foundSensor.orElseThrow(SensorNotFoundException::new);
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void save(SensorDTO sensorDTO) {
        sensorRepository.save(dtoConvertors.convertToSensor(sensorDTO));
    }

    @Transactional
    public void clearAllSensors() {
        measurementService.clearAllMeasurements();
        sensorRepository.deleteAll();
    }
}
