package ua.od.game.repository.dao;

import ua.od.game.model.AccountNotificationEntity;
import ua.od.game.model.NotificationEntity;

import java.util.List;

public interface AccountNotificationDao {
    Boolean clearAccountNotificationList(Integer accountId);
    List<AccountNotificationEntity> getAccountRecentNotificationList(Integer accountId);
}
