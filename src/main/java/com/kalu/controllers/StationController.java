package com.kalu.controllers;

import com.kalu.models.db.Measurement;
import com.kalu.models.db.Station;
import com.kalu.models.dto.StationDTO;
import com.kalu.repositories.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * Created by Albert on 13.01.2016.
 */
@Path("/stations")
public class StationController {

    static StationRepository repo = new StationRepositoryImpl();
    static MeasurementRepository mesRepo = new MeasurementRepositoryImpl();



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
        return repo.getAll().parallelStream()//
                                .map((bo)-> toDTO(bo))//
                                .collect(Collectors.toSet());
    }

    private StationDTO toDTO(Station station) {
        StationDTO dto = new StationDTO();
        dto.setId(station.getId());
        dto.setName(station.getName());
        dto.setHumidity(station.isHumidity());
        dto.setTemperature(station.isTemperature());
        dto.setSunshine(station.isSunshine());

        Set<Long> mess = station.getMeasurements().parallelStream()
                                    .map((mes)-> mes.getId())
                                    .collect(Collectors.toSet());
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

        Set<Measurement> mess = dto.getMeasurements().stream()
                                .map((id)-> mesRepo.get(id))
                                .collect(Collectors.toSet());
        station.setMeasurements(mess);
        return station;
    }
}
