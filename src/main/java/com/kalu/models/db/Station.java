package com.kalu.models.db;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Albert on 13.01.2016.
 */
@Entity
public class Station implements  DBObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "station")
    private Set<Measurement> measurements = new LinkedHashSet<>();

    @Basic
    private boolean temperature;

    @Basic
    private boolean humidity;

    @Basic
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
