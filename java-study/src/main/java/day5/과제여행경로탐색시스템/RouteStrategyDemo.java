package day5.과제여행경로탐색시스템;

import java.util.Scanner;

public class RouteStrategyDemo {
    public static void main(String[] args) {
        CitiesData citiesData = new CitiesData();
        citiesData.addData("서울", "대전", 90, 150, 10000);
        citiesData.addData("대전", "부산", 150, 200, 15000);
        citiesData.addData("서울", "부산", 240, 400, 25000);
        citiesData.addData("서울", "인천", 60, 50, 5000);
        citiesData.addData("대전", "인천", 120, 180, 8000);

        Scanner scanner = new Scanner(System.in);
        Navigator navigator = new Navigator();

        System.out.println("경로 탐색 전략을 선택하세요:");
        System.out.println("1. 가장 빠른 경로 (Fastest)");
        System.out.println("2. 가장 짧은 경로 (Shortest)");
        System.out.println("3. 가장 경제적인 경로 (Economic)");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                navigator.setStrategy(new FastestRouteStrategy(citiesData));
                break;
            case 2:
                navigator.setStrategy(new ShortestRouteStrategy(citiesData));
                break;
            case 3:
                navigator.setStrategy(new EconomicRouteStrategy(citiesData));
                break;
            default:
                System.out.println("잘못된 선택입니다. 가장 빠른 경로로 설정됩니다.");
                navigator.setStrategy(new FastestRouteStrategy(citiesData));
                break;
        }

        System.out.println("출발지를 입력하세요. (예:서울):");
        String start = scanner.nextLine();
        System.out.println("목적지를 입력하세요. (예:부산):");
        String end = scanner.nextLine();

        navigator.navigate(start, end);
    }
}