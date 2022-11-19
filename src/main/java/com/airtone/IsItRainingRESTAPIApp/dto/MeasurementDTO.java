package com.airtone.IsItRainingRESTAPIApp.dto;

import com.airtone.IsItRainingRESTAPIApp.models.Sensor;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Component
public class MeasurementDTO {

    @NotEmpty(message = "Value should not be empty")
    @Pattern(regexp = "^-?((100)(\\.0{1,2})?||(\\d{1,2})(\\.\\d{1,2})?)$"
            , message = "Value should be between -100 and 100")
    private String value;

    @NotEmpty(message = "raining should not be empty")
    @Pattern(regexp = "(true)|(false)", message = "Raining should be true or false")
    private String raining;

    private Sensor sensor;



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
