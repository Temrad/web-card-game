package ua.od.game.service.impl;

import ua.od.game.dto.AccountAchievementDto;
import ua.od.game.repository.dao.AccountAchievementDao;
import ua.od.game.service.AccountAchievementService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class AccountAchievementServiceImpl implements AccountAchievementService {

    @Inject
    private AccountAchievementDao accountAchievementDao;

    @Override
    public List<AccountAchievementDto> getAccountAchievementsList(Integer accountId) {
        final List<AccountAchievementDto> accountAchievementDtos = new LinkedList<>();
        accountAchievementDao.getUserAchievementsList(accountId).forEach(accountAchievementEntity -> accountAchievementDtos.add(new AccountAchievementDto() {{
            setId(accountAchievementEntity.getId());
            setAccountAchievementId(accountAchievementEntity.getAccountAchievementId());
            setAchievementId(accountAchievementEntity.getAchievementId());
            setAmount(accountAchievementEntity.getAmount());
        }}));
        return accountAchievementDtos;
    }
}
