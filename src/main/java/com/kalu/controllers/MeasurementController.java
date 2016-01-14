package com.kalu.controllers;

import com.kalu.models.db.Measurement;
import com.kalu.models.dto.MeasurmentDTO;
import com.kalu.models.dto.StationDTO;
import com.kalu.repositories.MeasurementRepository;
import com.kalu.repositories.MeasurementRepositoryImpl;
import com.kalu.repositories.StationRepository;
import com.kalu.repositories.StationRepositoryImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Albert on 14.01.2016.
 */
public class MeasurementController {
    static StationRepository stationRepo = new StationRepositoryImpl();
    static MeasurementRepository repo = new MeasurementRepositoryImpl();

    @POST
    @Path("/addOrEdit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MeasurmentDTO addOrEdit(MeasurmentDTO dto){

        Measurement measurment = fromDTO(dto);
        if (measurment.getId()!=null){
            measurment = repo.edit(measurment);
        } else {
            measurment = repo.add(measurment);
        }

        if (measurment != null){
            return toDTO(measurment);
        } else {
            return null;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MeasurmentDTO get(@PathParam("id") Long id){
        Measurement measurment = repo.get(id);

        if (measurment!=null){
            return this.toDTO(measurment);
        } else {
            return null;
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<MeasurmentDTO> getAll(){
        Set<MeasurmentDTO> dtos = repo.getAll().parallelStream()//
                                .map((bo) -> toDTO(bo))//
                                .collect(Collectors.toSet());// ;
        return dtos;
    }

    private MeasurmentDTO toDTO(Measurement bo) {
        MeasurmentDTO dto = new MeasurmentDTO();
        dto.setId(bo.getId());
        dto.setStationId(bo.getStation().getId());
        dto.setType(bo.getType());
        dto.setValue(bo.getValue());
        return dto;
    }

    private Measurement fromDTO(MeasurmentDTO dto) {
        Measurement bo = new Measurement();
        bo.setId(dto.getId());
        bo.setStation(stationRepo.get(dto.getId()));
        bo.setType(dto.getType());
        bo.setValue(dto.getValue());
        return bo;
    }
}
