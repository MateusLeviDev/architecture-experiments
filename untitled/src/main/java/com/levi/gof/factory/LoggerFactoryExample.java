package com.levi.gof.factory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

enum LogLevel {
    INFO, DEBUG, ERROR
}

interface Logger {
    void log(String message);
}

class ConsoleLogger implements Logger {
    private final LogLevel level;

    public ConsoleLogger(final LogLevel logLevel) {
        this.level = logLevel;
    }

    @Override
    public void log(String message) {
        System.out.println("[" + level + "] " + message);
    }
}

class FileLogger implements Logger {
    private final LogLevel level;
    private final String fileName;

    public FileLogger(LogLevel level, String fileName) {
        this.level = level;
        this.fileName = fileName;
    }

    @Override
    public void log(String message) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println("[" + level + "] " + message);
        } catch (IOException e) {
            throw new RuntimeException("error");
        }
    }
}

class LoggerFactory {
    public static Logger getLogger(final LogLevel level, final boolean toFile) {
        if (toFile) {
            return new FileLogger(level, "app.log");
        } else {
            return new ConsoleLogger(level);
        }
    }
}

public class LoggerFactoryExample {
    public static void main(String[] args) {
        Logger consoleLogger = LoggerFactory.getLogger(LogLevel.INFO, false);
        consoleLogger.log("Aplicação iniciada");

        Logger fileLogger = LoggerFactory.getLogger(LogLevel.ERROR, true);
        fileLogger.log("Ocorreu um erro na aplicação");

        Logger debugLogger = LoggerFactory.getLogger(LogLevel.DEBUG, false);
        debugLogger.log("Depurando a aplicação");
    }
}
