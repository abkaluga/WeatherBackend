package com.kalu.models.db;

import com.kalu.models.MeasurementType;

/**
 * Created by Albert on 13.01.2016.
 */
public class Measurement implements DBObject{

    private Long id;

    private Float value;

    private MeasurementType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public MeasurementType getType() {
        return type;
    }

    public void setType(MeasurementType type) {
        this.type = type;
    }
}
