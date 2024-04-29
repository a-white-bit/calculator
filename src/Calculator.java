import java.util.ArrayList;

public class Calculator {
    ArrayList<Double> result;

    public Calculator() {
        result = new ArrayList<Double>();
    }

    public double calculate(int num1, int num2, char operator) throws Exception {
        // 연산하기
        // 사칙연산이 아닌 값이 들어올 때 예외처리
        // Divide by 0 예외처리
        switch (operator) {
            case '+' -> result.add((double)num1 + num2);
            case '-' -> result.add((double)num1 - num2);
            case '*' -> result.add((double)num1 * num2);
            case '/' -> {
                if (num2 == 0) {
                    throw new Exception("0으로 나눌 수 없습니다.");
                }
                else result.add((double)num1 / (double)num2);
            }
            default -> throw new Exception("잘못된 연산기호를 입력하셨습니다.");
        }

        return result.get(result.size() - 1);
    }
}