package ua.od.game;

import ua.od.game.config.DataBaseConfig;
import ua.od.game.repository.helper.SqlHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseDeployer {
    private static final Logger LOG = Logger.getLogger(ApplicationStarter.class.getName());
    public static void main(String[] args) {
        createDb();
        deployDb();
    }

    public static void createDb() {
        SqlHelper.createStatement(statement -> {
            statement.executeUpdate("drop database if exists " + DataBaseConfig.DB_NAME);
            statement.executeUpdate("create database " + DataBaseConfig.DB_NAME);
            statement.executeUpdate("use " + DataBaseConfig.DB_NAME);
            return Void.TYPE;
        }, DataBaseConfig.DB_CONNECTION_URL);
    }

    public static void deployDb() {
        SqlHelper.createStatement(statement -> {
            File folder = new File(DataBaseConfig.DB_SCRIPTS_FOLDER);
            try {
                loadSqlFilesFromFolder(statement, folder);
            } catch (Exception e) {}
            return Void.TYPE;
        });
    }

    private static void loadSqlFilesFromFolder(Statement statement, final File folder) throws Exception {
        File[] files = folder.listFiles();
        Arrays.sort(files);
        for (final File file : files) {
            if (file.isDirectory()) {
                loadSqlFilesFromFolder(statement, file);
            } else if (file.getName().endsWith(".sql")){
                executeSqlFile(statement, file);
            }
        }
    }

    private static void executeSqlFile(Statement statement, File inputFile) {
        LOG.log(Level.INFO, "********Execute File: " + inputFile.getName() + " ********");
        Scanner scanner; // create scanner
        try {
            scanner = new Scanner(inputFile).useDelimiter(DataBaseConfig.DB_SCRIPT_DELIMITER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        while(scanner.hasNext()) { // loop through the sql file statements
            String rawStatement = scanner.next().trim();
            if(rawStatement.length() > 0) {
                rawStatement += DataBaseConfig.DB_SCRIPT_DELIMITER; // Get statement
                LOG.log(Level.INFO, "=======Execute Statement=======");
                LOG.log(Level.INFO, rawStatement.trim());
                try {
                    statement.execute(rawStatement);// Execute statement
                } catch (SQLException e) {
                    LOG.log(Level.WARNING, e.getMessage());
                }
                LOG.log(Level.INFO, "=======Execute Success!=======");
            }
        }
        scanner.close();
        LOG.log(Level.INFO, "********Execution of File: " + inputFile.getName() + " Success!********");
    }
}
