public class ArithmeticCalculator extends Calculator {
    private AbstractOperator operator;
    private double firstNumber;
    private double secondNumber;

    public void setOperator (AbstractOperator operator) {
        this.operator = operator;
    }

    public void setFirstNumber (double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber (double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public double getFirstNumber () {
        return firstNumber;
    }

    public double getSecondNumber () {
        return secondNumber;
    }

    // 연산하기 + 결과 리스트에 저장
    // Divide by 0 예외처리
    public void calculate() /*throws Exception*/ {
        double result = 0;
        try {
            result = operator.operate(firstNumber, secondNumber);
            addResult(result);
        } catch (ArithmeticException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }
}