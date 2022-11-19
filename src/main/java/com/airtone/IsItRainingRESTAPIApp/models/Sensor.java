package com.airtone.IsItRainingRESTAPIApp.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
//@Component
@Entity
@Table(name = "Sensor")
public class Sensor {
    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurement;

    public Sensor() {
    }

    public Sensor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }


    @Override
    public String toString() {
        return name;
    }
}
