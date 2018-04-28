package com.oath.snakewars.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.oath.common.snakewars.settings.GameSettings;
import com.oath.common.snakewars.settings.GameUpdate;
import com.oath.snakewars.common.EngineRequest;
import com.oath.snakewars.utils.SettingsProvider;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("oath/snakewars/playerservice")
@RequestScoped
public class GameResource
{
  @Inject
  public GameResource(){

  }
  @POST
  @Path("/settings")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response postSettings(
      final InputStream in,
      @Context final HttpServletRequest req
  ) throws IOException
  {
    //TODO Make mapper injectable
    ObjectMapper objectMapper = new ObjectMapper();
    GameSettings initialSettings = objectMapper.readValue(in, GameSettings.class);
    SettingsProvider.build(initialSettings);
    return Response.ok(ImmutableMap.of("receivedSettings", "true")).build();

  }
  @POST
  @Path("/update")
  @Consumes({MediaType.APPLICATION_JSON})
  public Response postUpdate(
      final InputStream in,
      @QueryParam("Test") final String cluster,
      @Context final HttpServletRequest req
  ) throws IOException
  {
    //TODO Make mapper injectable
    ObjectMapper objectMapper = new ObjectMapper();
    GameUpdate gameUpdate = objectMapper.readValue(in, GameUpdate.class);
    SettingsProvider.updateCurrentRound();
    SettingsProvider.updateGameBoard(gameUpdate.getGameBoard());
    if (SettingsProvider.getCurrentRound() == gameUpdate.getRoundNumber()) {
      return Response.ok(ImmutableMap.of("receivedUpdate", "true")).build();
    }
    else {
      return Response.ok(ImmutableMap.of("receivedUpdate", "false")).build();
    }
  }
  @GET
  @Path("/move")
  @Produces(MediaType.TEXT_PLAIN)
  public String sendNextMove() {
    return "Move type";
  }

}
