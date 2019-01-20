package ua.od.game.controller.impl;

import ua.od.game.controller.AccountAchievementController;
import ua.od.game.dto.AccountAchievementDto;
import ua.od.game.service.AccountAchievementService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/account")
public class AccountAchievementControllerImpl implements AccountAchievementController {

    @Inject
    private AccountAchievementService accountAchievementService;

    @GET
    @Path("achievement/{accountId}")
    public List<AccountAchievementDto> getAccountAchievementsList(@PathParam("accountId") Integer accountId) {
        return accountAchievementService.getAccountAchievementsList(accountId);

    }
}
