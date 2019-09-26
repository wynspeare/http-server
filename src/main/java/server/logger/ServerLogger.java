package server.logger;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class ServerLogger implements ILogger {
  private Logger logger;

  public ServerLogger(String directoryLocation){
    logger = Logger.getLogger(ServerLogger.class.getName());
    makeLogDirectory(directoryLocation);

    try {
      FileHandler fileHandler= new FileHandler(directoryLocation + "/serverLogger.log", 50000, 20, true);
      fileHandler.setFormatter(new SimpleFormatter());
      logger.addHandler(fileHandler);
    } catch (Exception e) {
      logger.warning("Failed to initialize logger handler");
    }
  }

  public void log(String message){
    logger.info(message);
  }

  public void log(String message, String method){
    logger.info("\"" + method + "\" - " + message);
  }


  public static void makeLogDirectory(String directory) {
    File logDir = new File(directory);
    if( !(logDir.exists()) )
      logDir.mkdirs();
  }
};
