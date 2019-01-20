package ua.od.game.repository.dao;

import ua.od.game.model.AccountAchievementEntity;

import java.util.List;

public interface AccountAchievementDao {
    List<AccountAchievementEntity> getUserAchievementsList(Integer accountId);
}
