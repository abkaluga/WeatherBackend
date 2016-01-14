package com.kalu.controllers;

import com.kalu.models.db.Measurement;
import com.kalu.models.db.Station;
import com.kalu.models.dto.StationDTO;
import com.kalu.repositories.MeasurementRepository;
import com.kalu.repositories.MeasurementRepositoryImplInMemory;
import com.kalu.repositories.StationRepository;
import com.kalu.repositories.StationRepositoryInMemoryInMemory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by Albert on 13.01.2016.
 */
@Path("/stations")
public class StationController {

    static StationRepository repo = new StationRepositoryInMemoryInMemory();
    static MeasurementRepository mesRepo = new MeasurementRepositoryImplInMemory();
    static {
        String[] names = {"0", "1","2","3"};
        boolean[] humAndTemp = {true, true, false, false};
        boolean[] sunshine = {true,false, true, false};

        for (int i=0;i< names.length;++i){
            Station st = new Station();
            st.setName(names[i]);
            st.setHumidity(humAndTemp[i]);
            st.setSunshine(sunshine[i]);
            repo.add(st);
        }
    }

    public StationController(){



    }


    @POST
    @Path("/addOrEdit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StationDTO addOrEdit(StationDTO dto) {
        Station station = this.fromDTO(dto);

        if (station.getId()!=null){
            station = repo.edit(station);
        } else {
            station = repo.add(station);
        }

        if (station != null){
            return toDTO(station);
        } else {
            return null;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StationDTO get(@PathParam("id") Long id){
        Station station = repo.get(id);

        if (station!=null){
            return this.toDTO(station);
        } else {
            return null;
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<StationDTO> getAll(){
        Set<Station> stations = repo.getAll();
        Set<StationDTO> dtos = new HashSet<StationDTO>(stations.size(),0.9f);
        for(Station station : stations){
            dtos.add(toDTO(station));
        }



        return dtos;
    }

    private StationDTO toDTO(Station station) {
        StationDTO dto = new StationDTO();
        dto.setId(station.getId());
        dto.setName(station.getName());
        dto.setHumidity(station.isHumidity());
        dto.setTemperature(station.isTemperature());
        dto.setSunshine(station.isSunshine());

        Set<Long> mess = new ConcurrentSkipListSet<Long>();
        for (Measurement mes : station.getMeasurements()){
            mess.add(mes.getId());
        }
        dto.setMeasurements(mess);
        return dto;
    }

    private Station fromDTO(StationDTO dto){
        Station station = new Station();
        station.setId(dto.getId());
        station.setName(dto.getName());
        station.setHumidity(dto.isHumidity());
        station.setTemperature(dto.isTemperature());
        station.setSunshine(dto.isSunshine());

        Set<Measurement> mess = new HashSet<Measurement>();
        for (Long mesId : dto.getMeasurements()){
            mess.add(mesRepo.get(mesId));
        }
        station.setMeasurements(mess);
        return station;
    }
}
