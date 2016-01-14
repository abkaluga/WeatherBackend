package com.kalu.models.dto;

/**
 * Created by Albert on 13.01.2016.
 */

import com.kalu.models.MeasurementType;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeasurmentDTO {

    @XmlElement
    private long id;

    @XmlElement
    private long stationId;

    @XmlElement
    private float value;

    @XmlElement
    private MeasurementType type;


    public String toString(){
        String builder = "[" +
                " Station id:" + this.getStationId() +
                " Value:" + this.getValue() +
                " of type" + this.getType().name() +
                ']';


        return builder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public MeasurementType getType() {
        return type;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }
}
