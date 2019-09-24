package server;

import java.io.File;
import java.util.logging.*;

public class ServerLogger {
  private Logger logger;

  public ServerLogger(){
    logger = Logger.getLogger(ServerLogger.class.getName());
    makeLogDirectory("Logs");

    try {
      FileHandler fileHandler= new FileHandler("Logs/serverLogger.log", 50000, 20, true);
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
