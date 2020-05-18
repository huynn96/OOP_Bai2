package com.logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Log2File implements ILogger {
    private String path;

    public Log2File(String path) {
        this.path = Paths.get(System.getProperty("user.dir"), path).toString();
    }

    public void Write(String message) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String timestamp = simpleDateFormat.format(new Date());
            Files.write(Paths.get(path), String.format("%s  %s\n", timestamp, message).getBytes(), APPEND, CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
