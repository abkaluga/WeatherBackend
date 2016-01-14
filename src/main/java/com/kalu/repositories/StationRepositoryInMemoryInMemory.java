package com.kalu.repositories;

import com.kalu.models.db.Station;

/**
 * Created by Albert on 13.01.2016.
 */
public class StationRepositoryInMemoryInMemory extends CrudRepositoryImplInMemory<Station> implements StationRepository{

    public StationRepositoryInMemoryInMemory(){
        super();

            String[] names = {"0", "1","2","3"};
            boolean[] humAndTemp = {true, true, false, false};
            boolean[] sunshine = {true,false, true, false};

            for (int i=0;i< names.length;++i){
                Station st = new Station();
                st.setName(names[i]);
                st.setHumidity(humAndTemp[i]);
                st.setSunshine(sunshine[i]);
                this.add(st);
            }

    }
}
