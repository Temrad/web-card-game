package ua.od.game.repository.dao.impl;

import org.junit.Test;
import ua.od.game.model.AccountNotificationEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class AccountNotificationDaoImplTest extends DbTest {
    private AccountNotificationDaoImpl accountNotificationDao = new AccountNotificationDaoImpl();

    @Test
    public void getAccountRecentNotificationListTest() {
        List<AccountNotificationEntity> accountNotificationEntities;
        accountNotificationEntities = accountNotificationDao.getAccountRecentNotificationList(1);
        System.out.println(accountNotificationEntities);
    }

    @Test
    public void clearAccountNotificationListTest() {
        boolean affected = accountNotificationDao.clearAccountNotificationList(1);
        System.out.println("affected:" + affected);
        assertFalse(accountNotificationDao.clearAccountNotificationList(1));
    }
}
