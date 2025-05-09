package day5.과제여행경로탐색시스템;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//    짧은 거리 클래스

public class ShortestRouteStrategy implements RouteStrategy {
    private final CitiesData citiesData;

    public ShortestRouteStrategy(CitiesData citiesData) {
        this.citiesData = citiesData;
    }

    @Override
    public void findRoute(String start, String end) {
        Map<String, Integer> distanceMap = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        List<String> citiesToProcess = new ArrayList<>();

        for (String city : citiesData.getCities()) {
            distanceMap.put(city, Integer.MAX_VALUE);
            citiesToProcess.add(city);
        }
        distanceMap.put(start, 0);

        while (!citiesToProcess.isEmpty()) {
            String current = null;
            int minDistance = Integer.MAX_VALUE;
            for (String city : citiesToProcess) {
                int distance = distanceMap.get(city);
                if (distance < minDistance) {
                    minDistance = distance;
                    current = city;
                }
            }

            citiesToProcess.remove(current);

            for (CitiesData.Data edge : citiesData.getData(current)) {
                int newDistance = distanceMap.get(current) + edge.distance;
                if (newDistance < distanceMap.get(edge.to)) {
                    distanceMap.put(edge.to, newDistance);
                    prev.put(edge.to, current);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }

        System.out.println("Shortest Route:");
        System.out.println("경로: " + String.join(" → ", path));
        System.out.println("거리: " + distanceMap.get(end) + "km");
    }
}
