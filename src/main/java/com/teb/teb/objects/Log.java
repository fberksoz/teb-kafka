package com.teb.teb.objects;

import com.teb.teb.Utils.TimeUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Timestamp timestamp;
    private LogLevel logLevel;
    @ManyToOne
    private City city;

    public Log() {
    }

    public Log(City city, LogLevel logLevel) {
        this.logLevel = logLevel;
        this.city = city;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Log(String logAsString) {
        String[] logParts = logAsString.split(" ");
        this.timestamp = TimeUtils.convertStringToTimeStamp(logParts[0] + " " + logParts[1]);
        this.city = new City(logParts[3], logParts[4]);
        this.logLevel = LogLevel.valueOf(logParts[2]);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return getTimestamp() + " " + getLogLevel() + " " + getCity().getName() + " " + getCity().getMessage();
    }
}
