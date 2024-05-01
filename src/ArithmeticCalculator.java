public class ArithmeticCalculator extends Calculator {

    private OperatorType operatorType;  // Enum 클래스를 활용
    private AbstractOperator operator;  // 추상 클래스를 활용
    private double firstNumber;
    private double secondNumber;


    // Enum 클래스 + 추상 클래스를 활용한 다형성
    // 매개변수: enum class {ADD, SUBTRACT, MULTIPLY, DIVIDE, MOD}
    public void setOperator (OperatorType type) {
        operatorType = type;
        switch (operatorType) {
            case ADD -> operator = new AddOperator();
            case SUBTRACT -> operator = new SubtractOperator();
            case MULTIPLY -> operator = new MultiplyOperator();
            case DIVIDE -> operator = new DivideOperator();
            case MOD -> operator = new ModOperator();
        }
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
    // Divide by 0 예외처리: ArithmeticException
    public void calculate() {
        double result = 0;
        try {
            // Enum과 추상 클래스를 이용한 다형성
            result = operator.operate(firstNumber, secondNumber);
            addResult(result);
        } catch (ArithmeticException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }
}