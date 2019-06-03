package com.teb.teb.Utils;

import com.teb.teb.objects.City;
import com.teb.teb.objects.Log;
import com.teb.teb.objects.LogLevel;

import java.util.Random;

public class LogCreator {

    public static String[] cityNames = new String[]{"Istanbul", "Tokyo", "Moskow", "Beijing", "London"};

    public static Log getLog() {
        return new Log(getRandomCity(), getRandomLogLevel());
    }

    private static City getRandomCity() {
        return new City(cityNames[new Random().nextInt(cityNames.length)]);
    }

    private static LogLevel getRandomLogLevel() {
        LogLevel[] logLevels = LogLevel.values();
        return logLevels[new Random().nextInt(logLevels.length)];
    }
}
