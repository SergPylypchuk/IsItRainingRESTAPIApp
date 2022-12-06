package com.airtone.IsItRainingRESTAPIApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ViewController {

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("/sensor/new")
    public String newSensor() {
        return "sensor/new";
    }

    @GetMapping("/measurement/new")
    public String newMeasurement() {
        return "measurement/new";
    }

    @GetMapping("/measurement/show")
    public String showAllMeasurements() {
        return "measurement/show";
    }

    @GetMapping("/measurement/rainydays")
    public String showAllRainyDays() {
        return "measurement/rainydays";
    }

    @GetMapping("/clear")
    public String clearAllData() {
        return "clear";
    }

}