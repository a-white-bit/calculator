public class ArithmeticCalculator extends Calculator {
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
        addResult(result);
    }
}