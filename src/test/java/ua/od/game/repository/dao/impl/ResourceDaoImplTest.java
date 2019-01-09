package ua.od.game.repository.dao.impl;

import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.ResourceEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ResourceDaoImplTest extends DbTest {

    ResourceDaoImpl resourceDao;

    @Before
    public void init() {
        resourceDao = new ResourceDaoImpl();
    }

    @Test
    public void getResourceListTest() {
        List<ResourceEntity> resources = resourceDao.getAllResourceList();
        for (int i = 0; i < resources.size(); i++) {
            System.out.print(resources.get(i).getId() + " ");
            System.out.print(resources.get(i).getName() + " ");
            System.out.print(resources.get(i).getDescription()+ " ");
            System.out.print(resources.get(i).getDefaultNumber()+ " ");
        }
        assertTrue(!resources.isEmpty());
    }

}
