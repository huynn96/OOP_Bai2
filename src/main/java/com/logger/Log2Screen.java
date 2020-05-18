package com.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log2Screen implements ILogger {
    public void Write(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timestamp = simpleDateFormat.format(new Date());
        System.out.printf("%s  %s\n", timestamp, message);
    }
}
