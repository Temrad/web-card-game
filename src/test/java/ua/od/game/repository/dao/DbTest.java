package ua.od.game.repository.dao;

import ua.od.game.DataBaseDeployer;
import ua.od.game.config.DataBaseConfig;

import java.io.File;
import java.io.IOException;

/**
 * @author ruslan.gramatic
 */
public class DbTest {
    static {
        // delete the H2 database from ./target/test-classes/test_db/ directory
        File file = new File(DataBaseConfig.ABSOLUTE_CLASSPATH + "/test_db");
        if (file.exists()) {
            try {
                delete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DataBaseDeployer.deployDb();
    }

    private static void delete(File file) throws IOException {
        for (File childFile : file.listFiles()) {
            if (childFile.isDirectory()) {
                delete(childFile);
            } else {
                if (!childFile.delete()) {
                    throw new IOException();
                }
            }
        }
        if (!file.delete()) {
            throw new IOException();
        }
    }
}
