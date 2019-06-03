package com.teb.teb.Utils;

import com.teb.teb.kafka.Producer;
import com.teb.teb.objects.Log;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class LogFileUtils {

    private static final String LOGS_PATH = "teblog";
    private static final String LOG_FILE_EXTENSION = "teblogs";
    private static final int FILE_LIMIT = 2097152; // 2MB in bytes
    private static String currentLogFilePath;
    private static long currentLineNumber = 0;

    static {
        currentLogFilePath = getNewestLogFile();
    }


    public static void addNewLogToLogFile(Log log) {
        File file = new File(currentLogFilePath);
        String data = log.toString();

        // create new log file if the log file exceeds 2MB
        if (file.length() > FILE_LIMIT) {
            currentLogFilePath = createNewFile();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getNewestLogFile() {
        File theNewestFile = null;
        File dir = new File(LOGS_PATH);
        FileFilter fileFilter = new WildcardFileFilter("*." + LOG_FILE_EXTENSION);
        File[] files = dir.listFiles(fileFilter);

        if (files != null && files.length > 0) {
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
            return theNewestFile.getPath();
        } else {
            // if no file exist in the folder
            return createNewFile();
        }
    }

    private static String createNewFile() {
        Path newFilePath = Paths.get(LOGS_PATH + "/" + System.currentTimeMillis() + "." + LOG_FILE_EXTENSION);
        try {
            Files.createFile(newFilePath);
            return newFilePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(currentLogFilePath))) {
            String line;
            long lineNumber = 0;
            boolean isCorrectLine = false;
            while ((line = br.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    isCorrectLine = true;
                }
                if (isCorrectLine) {
                    Producer.sendMessage(line);
                }
                lineNumber++;
            }
            currentLineNumber = lineNumber;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
