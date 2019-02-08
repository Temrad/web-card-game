package ua.od.game.repository.dao.impl;

import ua.od.game.model.NotificationEntity;
import ua.od.game.repository.dao.AccountNotificationDao;

import java.util.List;

public class AccountNotificationDaoImpl implements AccountNotificationDao {
    private static String GET_ACCOUNT_RECENT_NOTIFICATIONS =
            "select DeservedNotifications.ID from (\n" +
            "\n" +
            "    select notification.id from notification\n" +
            "    where notification.id IN (\n" +
            "\n" +
            "        select notification.id from notification\n" +
            "          inner join RESOURCE_SET\n" +
            "            on NOTIFICATION.RESOURCE_SET_ID = RESOURCE_SET.SET_ID\n" +
            "          inner join (select * from ACCOUNT_RESOURCE where ACCOUNT_ID = 2) as accResources\n" +
            "            on RESOURCE_SET.RESOURCE_ID = accResources.RESOURCE_ID\n" +
            "            and RESOURCE_SET.AMOUNT > 0\n" +
            "            and RESOURCE_SET.AMOUNT <= accResources.AMOUNT\n" +
            "          union (select notification.id from NOTIFICATION where RESOURCE_SET_ID is null))\n" +
            "\n" +
            "    and notification.id IN (\n" +
            "\n" +
            "        select notification.id from NOTIFICATION\n" +
            "        where notification.id NOT IN(\n" +
            "\n" +
            "              select notification.id from notification\n" +
            "                  inner join BUILDING_SET\n" +
            "                    on NOTIFICATION.BUILDING_SET_ID = BUILDING_SET.SET_ID\n" +
            "                  left join (select * from ACCOUNT_BUILDING where ACCOUNT_ID = 2) as accBuildings\n" +
            "                    on BUILDING_SET.BUILDING_ID = accBuildings.BUILDING_ID\n" +
            "                    and BUILDING_SET.AMOUNT <= accBuildings.AMOUNT\n" +
            "              where accBuildings.ID is null))\n" +
            "\n" +
            "    and notification.id IN (\n" +
            "\n" +
            "        select notification.id from NOTIFICATION\n" +
            "        where notification.id NOT IN(\n" +
            "\n" +
            "             select notification.id from notification\n" +
            "                  inner join UPGRADE_SET\n" +
            "                     on NOTIFICATION.UPGRADE_SET_ID = UPGRADE_SET.SET_ID\n" +
            "                  left join (select * from ACCOUNT_UPGRADE where ACCOUNT_ID = 2) as accUpgrades\n" +
            "                     on UPGRADE_SET.UPGRADE_ID = accUpgrades.UPGRADE_ID\n" +
            "                     and UPGRADE_SET.AMOUNT <= accUpgrades.AMOUNT\n" +
            "             where accUpgrades.ID is null))\n" +
            "    ) as DeservedNotifications\n" +
            "\n" +
            "    left join (select * from ACCOUNT_NOTIFICATION where ACCOUNT_ID = 2) as accNotifications\n" +
            "                   on DeservedNotifications.ID = accNotifications.NOTIFICATION_ID\n" +
            "    where accNotifications.ID is null;\n";

    @Override
    public Boolean clearAccountNotificationList(Integer accountId) {
        return null;
    }

    @Override
    public List<NotificationEntity> getAccountRecentNotificationList(Integer accountId) {
        return null;
    }
}
