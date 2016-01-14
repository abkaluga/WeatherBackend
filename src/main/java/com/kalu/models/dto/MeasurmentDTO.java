package com.kalu.models.dto;

/**
 * Created by Albert on 13.01.2016.
 */

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeasurmentDTO {

    private long stationId;
    @XmlAttribute
    private boolean temperature;

    @XmlAttribute
    private boolean humidity;

    @XmlAttribute
    private boolean sunshine;

    @XmlElement
    private float temperatureValue;
    @XmlElement
    private float humidityValue;
    @XmlElement
    private float sunshineValue;

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
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

    public void setSunshine(boolean sunshine) {
        this.sunshine = sunshine;
    }

    public float getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(float temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public float getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(float humidityValue) {
        this.humidityValue = humidityValue;
    }

    public float getSunshineValue() {
        return sunshineValue;
    }

    public void setSunshineValue(float sunshineValue) {
        this.sunshineValue = sunshineValue;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        builder.append(" Station id:").append(this.getStationId());
        builder.append(" Has temperature:").append(this.isTemperature());
        builder.append( "Has humidity:").append(this.isHumidity());
        builder.append( "Has sunshine").append(this.isSunshine());
        if (isTemperature()){
            builder.append(" T: ").append(this.getTemperatureValue());
        }
        if (isHumidity()){
            builder.append(" H:").append(this.getHumidityValue());
        }
        if (isSunshine()){
            builder.append(" S:").append(this.getSunshineValue());
        }

        builder.append(']');


        return builder.toString();
    }
}
