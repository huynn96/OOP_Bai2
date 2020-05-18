package com.logger;

import java.util.Base64;

public class App {
    public static void main(String[] args) {
        ILogger logger = Logger.getInstance().getLogger();
        logger.Write("test");
    }
}
