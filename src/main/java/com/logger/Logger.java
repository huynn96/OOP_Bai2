package com.logger;

import sun.misc.Resource;
import sun.security.util.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class Logger {
    private static Logger Instance = null;
    private ILogger logger;
    private Logger() {
        Properties loggerProps = new Properties();
        try {
            loggerProps.load(new FileInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResource("logger.properties")).getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String typeLogger = loggerProps.getProperty("logger.type", "console");
        switch (typeLogger) {
            case "email":
                logger = new Log2Email();
                break;
            case "file":
                String path = loggerProps.getProperty("logger.path", "log/logger.log");
                logger = new Log2File(path);
                break;
            case "console":
                logger = new Log2Screen();
                break;
            default:
                System.out.println("logger type is not valid");
                break;
        }
    }

    public static Logger getInstance()
    {
        if (Instance == null)
            Instance = new Logger();

        return Instance;
    }

    public ILogger getLogger() {
        return logger;
    }
}
