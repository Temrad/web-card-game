package ua.od.game.controller.impl;

import ua.od.game.controller.UserController;
import ua.od.game.dto.UserDto;
import ua.od.game.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/user")
public class UserControllerImpl implements UserController {

    @Inject
    public UserService userService;

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response loginUser(UserDto user) {
        String token = userService.loginUser(user);
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, user.toString());
        Cookie preCookie = new Cookie("token", token, "/", "", 1);
        NewCookie newCookie = new NewCookie(preCookie, "Added cookie and logged in", -1, false);
        return Response.ok().cookie(newCookie).build();
    }

    @GET
    @Path("logout")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response logoutUser(@CookieParam("token") Cookie cookie) {
        userService.logoutUser(cookie.getValue());
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, cookie.getValue());
        if (cookie != null) {
            Cookie preCookie = new Cookie("token", "", "/", "", 1);
            NewCookie newCookie = new NewCookie(preCookie, "Deleted cookie and logged out", -1, false);
            return Response.ok().cookie(newCookie).build();
        }
        return Response.ok().build();
    }

    @POST
    @Path("new")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createNewUser(UserDto user) {
        String token = userService.loginUser(user);
        if(token == null) {
            token = userService.createNewUser(user);
        }
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, user.toString());
        Cookie preCookie = new Cookie("token", token, "/", "", 1);
        NewCookie newCookie = new NewCookie(preCookie, "Created new user and logged in/Or login if this user is exist", -1, false);
        return Response.status(201).entity("User").cookie(newCookie).build();
    }
}

