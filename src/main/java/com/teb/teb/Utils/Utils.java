package com.teb.teb.Utils;

import com.teb.teb.objects.Log;

public class Utils {

    public static void generateContinuouslyLogToFile() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Log log = LogCreator.getLog();
                    LogFileUtils.addNewLogToLogFile(log);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public static void continuouslyReadFile() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    LogFileUtils.readFile();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}
