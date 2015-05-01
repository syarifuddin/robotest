package com.sen.toy.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sen.toy.command.CommandInvoker;
import com.sen.toy.models.Board;
import com.sen.toy.models.Position;
import com.sen.toy.models.Robot;
import com.sen.toy.models.RobotStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/robot")
public class RobotResource {

    private static final Logger LOG = LoggerFactory.getLogger(RobotResource.class);
    private static final Board BOARD = new Board(5,5);

    @Path("/{robotName}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response create(

        @PathParam("robotName") String robotName
        ) {
            Robot robot = new Robot(robotName, BOARD);
            if (!RobotStorage.addNewRobot(robot)){
                return Response.status(Response.Status.SEE_OTHER).build();
            }
            return Response.ok(robot.getName()).build();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public List<String> list( ) {
        return RobotStorage.getAllRobotsName();
    }

    @Path("/{robotName}/position/{command}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response change(

        @PathParam("robotName") String robotName,
        @PathParam("command") String command
        ) {

        Robot theRobot = RobotStorage.findRobotBy(robotName);
        CommandInvoker.invoke(command, theRobot);
        return buildResponse(theRobot, theRobot.getPosition()).build();
    }

  @Path("/{robotName}/position/{command}/{positionId}")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  public Response changePost(

    @PathParam("robotName") String robotName,
    @PathParam("command") String command,
    @PathParam("positionId") String positionId
  ) {

    Robot theRobot = RobotStorage.findRobotBy(robotName);
    CommandInvoker.invoke(command, theRobot);
    return buildResponse(theRobot, theRobot.getPosition()).build();
  }

    @Path("/{robotName}/position/{positionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response report(
        @PathParam("robotName") String robotName,
        @PathParam("positionId") String positionId
        ) {
        Robot theRobot = RobotStorage.findRobotBy(robotName);
        return buildResponse(theRobot, theRobot.getPosition()).build();
    }

    private Response.ResponseBuilder buildResponse(Robot theRobot, Object data){
        if (theRobot == null){
            return Response.status(Response.Status.NOT_FOUND);
        }
        else if(!theRobot.isOnBoard()){
            return Response.status(Response.Status.NO_CONTENT);
        }
        return Response.ok(data);
    }


    @Path("/{robotName}/position")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response place(

        @PathParam("robotName") String robotName,
        String position
        ) {
        Robot theRobot = RobotStorage.findRobotBy(robotName);
        if (theRobot == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ObjectMapper mapper = new ObjectMapper();
        Position thePosition;
        try {
             thePosition = mapper.readValue(position, Position.class);
             theRobot.place(thePosition);

        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        // robot placement position is rejected e.g : outside of board
        if (theRobot.getPosition()==null){
          return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(theRobot.getPosition()).build();
    }



}
