package day5.과제여행경로탐색시스템;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//    빠른 경로 클래스

public class FastestRouteStrategy implements RouteStrategy {
    private final CitiesData citiesData;

    public FastestRouteStrategy(CitiesData citiesData) {
        this.citiesData = citiesData;
    }

    @Override
    public void findRoute(String start, String end) {
        Map<String, Integer> timeMap = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        List<String> citiesToProcess = new ArrayList<>();

        for (String city : citiesData.getCities()) {
            timeMap.put(city, Integer.MAX_VALUE);
            citiesToProcess.add(city);
        }
        timeMap.put(start, 0);

        while (!citiesToProcess.isEmpty()) {
            String current = null;
            int minTime = Integer.MAX_VALUE;
            for (String city : citiesToProcess) {
                int time = timeMap.get(city);
                if (time < minTime) {
                    minTime = time;
                    current = city;
                }
            }

            citiesToProcess.remove(current);

            for (CitiesData.Data edge : citiesData.getData(current)) {
                int newTime = timeMap.get(current) + edge.time;
                if (newTime < timeMap.get(edge.to)) {
                    timeMap.put(edge.to, newTime);
                    prev.put(edge.to, current);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }


        System.out.println("Fastest Route:");
        System.out.println("경로: " + String.join(" → ", path));
        System.out.println("시간: " + timeMap.get(end) + "분");
    }
}
