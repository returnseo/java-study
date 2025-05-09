package day5.과제여행경로탐색시스템;

// 도시정보 클래스

import java.util.*;

public class CitiesData {
    public static class Data {
        String to;
        int time;    // 시간
        int distance; // 거리
        int cost;    // 가격

        public Data(String to, int time, int distance, int cost) {
            this.to = to;
            this.time = time;
            this.distance = distance;
            this.cost = cost;
        }
    }

    private final Map<String, List<Data>> dataList = new HashMap<>();

    public void addData(String from, String to, int time, int distance, int cost) {
        if (!dataList.containsKey(from)) {
            dataList.put(from, new ArrayList<>());
        }
        dataList.get(from).add(new Data(to, time, distance, cost));

        if (!dataList.containsKey(to)) {
            dataList.put(to, new ArrayList<>());
        }
        dataList.get(to).add(new Data(from, time, distance, cost));
    }

    public List<Data> getData(String from) {
        return dataList.getOrDefault(from, new ArrayList<>());
    }

    public Set<String> getCities() {
        return dataList.keySet();
    }
}