package com.kalu.models.dto;

import com.kalu.models.db.Measurement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Albert on 13.01.2016.
 */
@XmlRootElement
public class StationDTO {
    @XmlElement
    private Long id;

    @XmlElement
    private String name;

    @XmlElement
    private Set<Long> measurements = new LinkedHashSet<Long>();

    @XmlElement
    private boolean temperature;

    @XmlElement
    private boolean humidity;

    @XmlElement
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

    public Set<Long> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Long> measurements) {
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

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(" id:").append(this.getId());
        builder.append(" name:").append(this.getName());
        builder.append(" Has temperature:").append(this.isTemperature());
        builder.append( "Has humidity:").append(this.isHumidity());
        builder.append( "Has sunshine").append(this.isSunshine());

        return builder.toString();

    }
}
