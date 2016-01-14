package com.kalu.models.db;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Albert on 13.01.2016.
 */
public class Station implements  DBObject{

    private Long id;

    private String name;

    private Set<Measurement> measurements = new LinkedHashSet<Measurement>();

    private boolean temperature;

    private boolean humidity;

    private boolean sunshine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }

    public boolean isTemperature() {
        return temperature;
    }

    public void setTemperature(boolean temperature) {
        this.temperature = temperature;
    }

    public boolean isHumidity() {
        return humidity;
    }

    public void setHumidity(boolean humidity) {
        this.humidity = humidity;
    }

    public boolean isSunshine() {
        return sunshine;
    }

    public void setSunshine(boolean sunshitne) {
        this.sunshine = sunshitne;
    }
}
