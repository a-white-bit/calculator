public class CircleCalculator extends Calculator {
    // 인스턴스에 상관 없는 불변 상수 π (final, static)
    private final static float PIE = 3.141592653589793f;
    private double radius;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    // 원의 넓이를 구하기 + 리스트에 결과 저장
    public void calculate() {
        double result = PIE * this.radius * this.radius;
        addResult(result);
    }
}