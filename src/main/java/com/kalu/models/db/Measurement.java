package com.kalu.models.db;

import com.kalu.models.MeasurementType;
import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by Albert on 13.01.2016.
 */
@Entity
public class Measurement implements DBObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float value;

    @Enumerated(EnumType.ORDINAL)
    private MeasurementType type;

    @ManyToOne
    @NotNull
    private Station station;

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

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
