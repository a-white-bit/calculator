import java.util.ArrayList;

public class Calculator {
    // 리스트 캡슐화 (private)
    // 리스트 참조 변경 불가 (final)
    private final ArrayList<Double> resultList;
    private final ArrayList<Double> circleAreaResult;
    // 인스턴스에 상관 없는 불변 상수 π (final, static)
    private final static float PIE = 3.141592653589793f;

    public Calculator() {
        // 리스트 초기화
        resultList = new ArrayList<>();
        circleAreaResult = new ArrayList<>();
    }

    // 연산하기 + 결과 리스트에 저장
    // 사칙연산이 아닌 값이 들어올 때, Divide by 0 예외처리
    public void calculate(int num1, int num2, char operator) throws Exception {
        double result = 0;
        switch (operator) {
            case '+' -> result = (double)num1 + num2;
            case '-' -> result = (double)num1 - num2;
            case '*' -> result = (double)num1 * num2;
            case '/' -> {
                if (num2 == 0) throw new Exception("0으로 나눌 수 없습니다.");
                else result = (double)num1 / num2;
            }
            default -> throw new Exception("잘못된 연산기호를 입력하셨습니다.");
        }
        resultList.add(result);
    }

    // 마지막 결과값 get
    public double getLastResult() throws IndexOutOfBoundsException {
        if (resultList.isEmpty()) throw new IndexOutOfBoundsException("빈 리스트입니다.");
        else return resultList.get(resultList.size() - 1);
    }
    // 인덱스 접근
    // 잘못된 인덱스 접근 예외 처리 필요함
    public double getListByIndex(int index) {
        return resultList.get(index);
    }
    // 리스트 크기
    public int getNumberOfList() {
        return resultList.size();
    }

    // 리스트 전부 출력
    public void inquiryResults() {
        if (resultList.isEmpty())
            System.out.print("[연산 결과]: (빈 리스트)");
        else
            resultList.forEach(result -> System.out.print(result + " "));

        System.out.println();
    }

    // 리스트 가장 오래된 요소 삭제
    public void removeFirstResult() throws Exception {
        if (resultList.isEmpty()) throw new Exception("빈 리스트입니다.");
        else resultList.remove(0);
    }









    // 원의 넓이를 구하기 + 리스트에 결과 저장
    public void calculateCircleArea(double radius) {
        double result = PIE * radius * radius;
        circleAreaResult.add(result);
    }

    // 마지막 결과값 get
    public double getLastCircleArea() throws IndexOutOfBoundsException {
        if (circleAreaResult.isEmpty()) throw new IndexOutOfBoundsException("빈 리스트입니다.");
        else return circleAreaResult.get(circleAreaResult.size() - 1);
    }
    // 인덱스 접근
    // 잘못된 인덱스 접근 예외 처리 필요함
    public double getCircleAreaByIndex(int index) {
        return circleAreaResult.get(index);
    }
    // 리스트 크기
    public int getNumberOfCircleArea() {
        return circleAreaResult.size();
    }

    // 리스트 전부 출력
    public void inquiryCircleArea() {
        if (circleAreaResult.isEmpty())
            System.out.print("[연산 결과]: (빈 리스트)");
        else
            circleAreaResult.forEach(result -> System.out.print(result + " "));
        System.out.println();
    }

    // 가장 오래된 요소 삭제
    public void removeFirstCircleArea() throws Exception {
        if (circleAreaResult.isEmpty()) throw new Exception("빈 리스트입니다.");
        else circleAreaResult.remove(0);
    }


}