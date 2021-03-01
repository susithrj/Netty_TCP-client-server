package com.srj.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Settings {

    public static String host = null;
    public static int port = 0;
    public static String userID = null;
    public static String password = null;
    public static long sequenceNo =0;

    public static void load(String filePath) {
        Logger logger = Logger.getLogger("Settings");
       // FileInputStream fis = new FileInputStream(filePath);
        try {
            Properties appSettings = new Properties();
            FileInputStream fis = new FileInputStream("configs/config.ini");
            appSettings.load(fis);
            host = ((String) appSettings.get("host"));
            port = Integer.parseInt((String) appSettings.get("port"));
            userID = ((String) appSettings.get("userID"));
            password = ((String) appSettings.get("password"));
           sequenceNo = (Long.parseLong((String)(appSettings.get("sequenceNo"))) );
            fis.close();
        } catch (IOException e) {
            logger.info("Could not load settings file.");
            logger.info(e.getMessage());
        }
    }

}
