package com.airtone.IsItRainingRESTAPIApp.util.services;

import com.airtone.IsItRainingRESTAPIApp.models.Measurement;
import com.airtone.IsItRainingRESTAPIApp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        measurement.setAddedAt(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public String rainyDaysCount() {
        return Long.toString(measurementRepository
                .findByRaining(true).stream().count());
    }

    @Transactional
    public void clearAllMeasurements() {
        measurementRepository.deleteAll();
    }

}
