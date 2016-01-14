package com.kalu.repositories;

import com.kalu.models.db.Station;

/**
 * Created by Albert on 14.01.2016.
 */
public class StationRepositoryImpl extends CrudRepositoryImpl<Station> implements StationRepository  {
    @Override
    protected Class<Station> getInnerClass() {
        return Station.class;
    }

    @Override
    protected void initObject(Station station) {
        station.getMeasurements().size();
    }
}
