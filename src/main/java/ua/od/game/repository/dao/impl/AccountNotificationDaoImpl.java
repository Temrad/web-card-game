package ua.od.game.repository.dao.impl;

import ua.od.game.model.AccountNotificationEntity;
import ua.od.game.repository.dao.AccountNotificationDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountNotificationDaoImpl implements AccountNotificationDao {
    private static String GET_ACCOUNT_RECENT_NOTIFICATIONS =
            "select DeservedNotifications.ID from (\n" +
            "  -- get notifications deserved according to account`s resources & buildings & upgrades\n" +
            "  select notification.id from notification\n" +
            "  where notification.id IN (\n" +
            "    -- get notifications which the account deserves according to it`s resources:\n" +
            "    select notification.id from notification\n" +
            "      inner join RESOURCE_SET\n" +
            "        on NOTIFICATION.RESOURCE_SET_ID = RESOURCE_SET.SET_ID\n" +
            "      inner join (select * from ACCOUNT_RESOURCE where ACCOUNT_ID = ?) as accResorces\n" +
            "        on RESOURCE_SET.RESOURCE_ID = accResorces.RESOURCE_ID\n" +
            "        and RESOURCE_SET.AMOUNT > 0\n" +
            "        and RESOURCE_SET.AMOUNT <= accResorces.AMOUNT\n" +
            "    union (select notification.id from NOTIFICATION where RESOURCE_SET_ID is null))\n" +
            "\n" +
            "  and notification.id IN (\n" +
            "    -- get notifications which the account deserves according to it`s buildings:\n" +
            "    select notification.id from NOTIFICATION\n" +
            "    where notification.id NOT IN(\n" +
            "      -- get notifications which are NOT deserved according to account`s buildings:\n" +
            "      select notification.id from notification\n" +
            "        inner join BUILDING_SET\n" +
            "          on NOTIFICATION.BUILDING_SET_ID = BUILDING_SET.SET_ID\n" +
            "        left join (select * from ACCOUNT_BUILDING where ACCOUNT_ID = ?) as accBuildings\n" +
            "          on BUILDING_SET.BUILDING_ID = accBuildings.BUILDING_ID                                                                                                                          and BUILDING_SET.AMOUNT <= accBuildings.AMOUNT\n" +
            "      where accBuildings.ID is null))\n" +
            "\n" +
            "  and notification.id IN (\n" +
            "    -- get notifications which the account deserves according to it`s upgrades:\n" +
            "    select notification.id from NOTIFICATION\n" +
            "    where notification.id NOT IN(\n" +
            "      -- get notifications which are NOT deserved according to account`s upgrades:\n" +
            "      select notification.id from notification\n" +
            "        inner join UPGRADE_SET\n" +
            "          on NOTIFICATION.UPGRADE_SET_ID = UPGRADE_SET.SET_ID\n" +
            "        left join (select * from ACCOUNT_UPGRADE where ACCOUNT_ID = ?) as accUpgrades\n" +
            "          on UPGRADE_SET.UPGRADE_ID = accUpgrades.UPGRADE_ID                                                                                                                              and UPGRADE_SET.AMOUNT <= accUpgrades.AMOUNT\n" +
            "      where accUpgrades.ID is null))\n" +
            ") as DeservedNotifications\n" +
            "  -- get rid of notifications which have been shown to this acc already:\n" +
            "  left join (select * from ACCOUNT_NOTIFICATION where ACCOUNT_ID = ?) as accNotifications\n" +
            "    on DeservedNotifications.ID = accNotifications.NOTIFICATION_ID\n" +
            "where accNotifications.ID is null;";

    private static String CLEAR_ACC_NOTIFICATIONS = "delete from ACCOUNT_NOTIFICATION where ACCOUNT_ID = ?;";
    private static String UPDATE_ACC_NOTIFICATIONS =
            "insert into ACCOUNT_NOTIFICATION (ACCOUNT_ID, NOTIFICATION_ID) values (?,?);";

    @Override
    public Boolean clearAccountNotificationList(Integer accountId) {
        int affectedRows =
        SqlHelper.prepareStatement(CLEAR_ACC_NOTIFICATIONS, statement -> {
            statement.setInt(1, accountId);
            return statement.executeUpdate();
        });
        return affectedRows > 0;
    }

    @Override
    public List<AccountNotificationEntity> getAccountRecentNotificationList(Integer accountId) {
        List<AccountNotificationEntity> notificationList = new ArrayList<>();
        SqlHelper.prepareStatement(GET_ACCOUNT_RECENT_NOTIFICATIONS, statement -> {
            for(int paramIndex = 1; paramIndex <= 4; paramIndex++)
                statement.setInt(paramIndex, accountId);
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() )
                notificationList.add(new AccountNotificationEntity() {{
                    setNotificationId(resultSet.getInt(1));
                }});
            return null;
        });
        updateAccountNotification(notificationList, accountId);
        return notificationList;
    }

    private void updateAccountNotification(List<AccountNotificationEntity> notificationList, Integer accountId) {
        SqlHelper.prepareStatement(UPDATE_ACC_NOTIFICATIONS, statement -> {
            statement.setInt(1, accountId);
            for (AccountNotificationEntity notification : notificationList) {
                statement.setInt(2, notification.getNotificationId());
                statement.executeUpdate();
            }
            return null;
        });
    }
}
