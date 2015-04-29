package com.sen.toy.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sen.toy.models.Position;
import io.dropwizard.jersey.params.BooleanParam;
import io.dropwizard.jersey.params.DateTimeParam;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/robot")
public class RobotResource {

    private static final Logger LOG = LoggerFactory.getLogger(RobotResource.class);


    @Path("/{robotName}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response create(

        @PathParam("robotName") String robotName
        ) {
            if ("megatron".equalsIgnoreCase(robotName)){
                return Response.status(Response.Status.SEE_OTHER).build();
            }
            return Response.ok(robotName).build();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public List<String> list(


        ) {
//
        List<String> list = new ArrayList<String>();

        String string1 = "Bumblebee";
        list.add(string1);
        list.add("Ironman");
        list.add("C3PO");
        return list;

    }

    @Path("/{robotName}/position/{command}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Position change(

        @PathParam("robotName") String robotName,
        @PathParam("command") String command
        ) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Received parameters:\n");
//
//        sb.append("robotName=");
//        sb.append(robotName);
//        sb.append("\n");
//
//        sb.append("command=");
//        sb.append(command);
//        sb.append("\n");
//
//        return sb.toString();
        Position position =  new Position();
        position.setAngle(Position.Angle.SOUTH);
        position.setX_pos(5);
        position.setY_pos(2);
        return position;
    }

    @Path("/{robotName}/position/{positionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response report(

        @PathParam("robotName") String robotName,
        @PathParam("positionId") String positionId
        ) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Received parameters:\n");
//
//        sb.append("robotName=");
//        sb.append(robotName);
//        sb.append("\n");
//
//        sb.append("positionId=");
//        sb.append(positionId);
//        sb.append("\n");
//
//        return sb.toString();
            if ("megatron".equalsIgnoreCase(robotName)) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

        Position position =  new Position();
        position.setAngle(Position.Angle.EAST);
        position.setX_pos(2);
        position.setY_pos(3);
        return Response.ok(position).build();
    }

    @Path("/{robotName}/position")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public String place(

        @PathParam("robotName") String robotName,
        String position
        ) {
        ObjectMapper mapper = new ObjectMapper();
        Position thePosition = new Position();
        try {
             thePosition = mapper.readValue(position, Position.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Received parameters:\n");

        sb.append("robotName=");
        sb.append(robotName);
        sb.append("\n");

        sb.append("position=");
        sb.append(thePosition.toString());
        sb.append("\n");
        return sb.toString();
    }

}
