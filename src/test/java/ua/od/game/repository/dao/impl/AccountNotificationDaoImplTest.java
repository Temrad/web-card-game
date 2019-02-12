package ua.od.game.repository.dao.impl;

import org.junit.Test;
import ua.od.game.model.AccountNotificationEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountNotificationDaoImplTest extends DbTest {
    private AccountNotificationDaoImpl accountNotificationDao = new AccountNotificationDaoImpl();
    private final Integer accountId = 1;

    @Test
    public void getAccountRecentNotificationListTest() {
        List<AccountNotificationEntity> accountNotificationEntities;
        accountNotificationEntities = accountNotificationDao.getAccountRecentNotificationList(accountId);
        // must return at least 'show-from-scratch' notifications:
        assertFalse(accountNotificationEntities.isEmpty());
        System.out.println(accountNotificationEntities);
        // must return nothing - account_notification has been updated in prev call
        assertTrue(accountNotificationDao.getAccountRecentNotificationList(accountId).isEmpty());
        // removes all from account_notification
        clearAccountNotificationListTest();
        // must return at least 'show-from-scratch' notifications:
        assertFalse(accountNotificationDao.getAccountRecentNotificationList(accountId).isEmpty());
    }

    @Test
    public void clearAccountNotificationListTest() {
        boolean affected = accountNotificationDao.clearAccountNotificationList(accountId);
        System.out.println("affected:" + affected);
        // second call has nothing to clear, always returns false:
        assertFalse(accountNotificationDao.clearAccountNotificationList(accountId));
    }
}
