package com.teb.teb.objects;

import com.teb.teb.Utils.LogCreator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class LiveData {

    private static ArrayList<String> dataList;
    private static Map<String, Integer> statMap;

    static {
        dataList = new ArrayList<>();
        statMap = new LinkedHashMap<>();
        initOrClearMap();
    }

    public static void addLog(Log log) {
        String cityName = log.getCity().getName();
        int count = statMap.get(cityName);
        statMap.put(cityName, count + 1);
    }

    public static String getData() {
        String stats = getStatFromMap();
        addToList(stats);
        initOrClearMap();
        return convertListToCSV(dataList);
    }

    public static String convertListToCSV(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : statMap.entrySet()) {
            if(sb.length() != 0){
                sb.append(",");
            }
            sb.append(entry.getKey());
        }
        for (String s : list) {
            sb.append(System.getProperty("line.separator"));
            sb.append(s);
        }
        return sb.toString();
    }

    public static String getStatFromMap() {
        StringBuilder sb = new StringBuilder();
        statMap.put("Time", (int) (System.currentTimeMillis() / 1000));
        for (Map.Entry entry : statMap.entrySet()) {
            if(sb.length() != 0){
                sb.append(",");
            }
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    public static void initOrClearMap() {
        statMap.put("Time", 0);
        for (String city : LogCreator.cityNames) {
            statMap.put(city, 0);
        }
    }

    public static void addToList(String mapstat) {
        dataList.add(mapstat);
        // remove oldest one if data list size is bigger than 10
        if (dataList.size() > 10) {
            dataList.remove(0);
        }
    }

}
