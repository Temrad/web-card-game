package ua.od.game.repository.dao;

import org.h2.tools.DeleteDbFiles;
import ua.od.game.DataBaseDeployer;
import ua.od.game.config.DataBaseConfig;

/**
 * @author ruslan.gramatic
 */
public class DbTest {
    static {
        // delete the H2 database named 'test' in the user home directory
        DeleteDbFiles.execute(DataBaseConfig.ABSOLUTE_CLASSPATH, "test", true);
        DataBaseDeployer.deployDb();
    }
}
