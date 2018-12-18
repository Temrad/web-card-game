package ua.od.game;

import java.util.logging.Level;
import java.util.logging.Logger;

import ua.od.game.config.AppContextConfig;
import org.eclipse.jetty.server.Server;

public class ApplicationStarter {
    public static void main(String[] args) throws Exception {
        Server jettyServer;
        if (args != null && args.length > 0 && args[0].equals("deploy")) {
            DataBaseDeployer.createDb();
            DataBaseDeployer.deployDb();
            jettyServer = new Server(Integer.valueOf(System.getenv("PORT")));
        } else {
            jettyServer = new Server(9090);
        }

        boolean debug = false;
        jettyServer.setHandler(new AppContextConfig().getHandlersConfig());
        try {
            jettyServer.start();
            if(debug) jettyServer.dumpStdErr();
            jettyServer.join();
        } catch (Exception e) {
            Logger.getLogger(ApplicationStarter.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            jettyServer.destroy();
        }
    }
}
