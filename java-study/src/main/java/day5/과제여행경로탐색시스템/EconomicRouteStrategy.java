package day5.과제여행경로탐색시스템;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//    경제적인 경로 클래스

public class EconomicRouteStrategy implements RouteStrategy {
    private final CitiesData citiesData;

    public EconomicRouteStrategy(CitiesData citiesData) {
        this.citiesData = citiesData;
    }

    @Override
    public void findRoute(String start, String end) {
        Map<String, Integer> costMap = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        List<String> citiesToProcess = new ArrayList<>();

        for (String city : citiesData.getCities()) {
            costMap.put(city, Integer.MAX_VALUE);
            citiesToProcess.add(city);
        }
        costMap.put(start, 0);

        while (!citiesToProcess.isEmpty()) {
            String current = null;
            int minCost = Integer.MAX_VALUE;
            for (String city : citiesToProcess) {
                int cost = costMap.get(city);
                if (cost < minCost) {
                    minCost = cost;
                    current = city;
                }
            }

            citiesToProcess.remove(current);

            for (CitiesData.Data edge : citiesData.getData(current)) {
                int newCost = costMap.get(current) + edge.cost;
                if (newCost < costMap.get(edge.to)) {
                    costMap.put(edge.to, newCost);
                    prev.put(edge.to, current);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }

        System.out.println("Economic Route:");
        System.out.println("경로: " + String.join(" → ", path));
        System.out.println("비용: " + costMap.get(end) + "원");
    }
}
