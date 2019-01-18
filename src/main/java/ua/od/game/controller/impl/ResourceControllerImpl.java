package ua.od.game.controller.impl;

import ua.od.game.controller.ResourceController;
import ua.od.game.dto.ResourceDto;
import ua.od.game.service.ResourceService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/resource")
public class ResourceControllerImpl implements ResourceController {

    @Inject
    public ResourceService resourceService;

    @GET
    @Path("list")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ResourceDto> getAllResourceList() {
        List<ResourceDto> resourceList = resourceService.getAllResourceList();
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, resourceList.toString());
        return resourceList;
    }
}
