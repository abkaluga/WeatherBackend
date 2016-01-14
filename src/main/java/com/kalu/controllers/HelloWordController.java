package com.kalu.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Albert on 13.01.2016.
 */
@Path("/hello")
public class HelloWordController {

    @GET
    @Path("/{param}")
    public Response sayHello(@PathParam("param") String msg){
        String output = "Hello "+ msg;
        return Response.ok().entity(output).build();
    }
}
