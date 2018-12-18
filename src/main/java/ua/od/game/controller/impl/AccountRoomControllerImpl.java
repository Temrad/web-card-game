package ua.od.game.controller.impl;

import ua.od.game.controller.AccountRoomController;
import ua.od.game.dto.AccountRoomDto;
import ua.od.game.service.RoomService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/room")
public class AccountRoomControllerImpl implements AccountRoomController {

    @Inject
    public RoomService roomService;

    @GET
    @Path("account/list")
    public List<AccountRoomDto> getAccountRoomList() {
        return Collections.emptyList(); //TODO: we need to return this list with correct data
    }

    @GET
    @Path("{roomId}/account/{accountId}/join")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response joinRoom(@PathParam("roomId") Integer roomId, @PathParam("accountId") Integer accountId) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "room=" + roomId);
        roomService.joinRoom(roomId, accountId);
        return Response.status(200).entity("User Entered").build();
    }

    @GET
    @Path("{roomId}/account/{accountId}/leave")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response leaveRoom(@PathParam("roomId") Integer roomId, @PathParam("accountId") Integer accountId) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"room=" + roomId);
        roomService.leaveRoom(accountId);
        return Response.status(200).entity("User Left").build();
    }
}
