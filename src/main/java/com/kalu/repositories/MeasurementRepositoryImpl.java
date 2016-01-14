package com.kalu.repositories;

import com.kalu.models.db.Measurement;
import org.hibernate.Hibernate;

/**
 * Created by Albert on 14.01.2016.
 */
public class MeasurementRepositoryImpl extends CrudRepositoryImpl<Measurement> implements MeasurementRepository {
    @Override
    protected Class<Measurement> getInnerClass() {
        return Measurement.class;
    }

    @Override
    protected void initObject(Measurement measurement) {
        measurement.getStation().toString();
    }
}
