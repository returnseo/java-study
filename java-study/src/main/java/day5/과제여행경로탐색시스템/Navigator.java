package day5.과제여행경로탐색시스템;


public class Navigator {
    private RouteStrategy strategy;

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void navigate(String start, String end) {
        if (strategy == null) {
            System.out.println("경로 탐색 전략이 설정되지 않았습니다.");
            return;
        }
        strategy.findRoute(start, end);
    }
}
