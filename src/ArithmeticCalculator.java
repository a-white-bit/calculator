public class ArithmeticCalculator<T extends Number> extends Calculator {

    private OperatorType operatorType;  // Enum 클래스를 활용
    private AbstractOperator<T> operator;  // 추상 클래스를 활용
    private T firstNumber;
    private T secondNumber;
    private final Class<T> dataType;


    public ArithmeticCalculator(Class<T> type) {
        this.dataType = type;
    }

    // Enum 클래스 + 추상 클래스를 활용한 다형성
    // 매개변수: enum class {ADD, SUBTRACT, MULTIPLY, DIVIDE, MOD}
    public void setOperator (OperatorType type) {
        operatorType = type;
        switch (operatorType) {
            case ADD -> operator = new AddOperator<>(dataType);
            case SUBTRACT -> operator = new SubtractOperator<>(dataType);
            case MULTIPLY -> operator = new MultiplyOperator<>(dataType);
            case DIVIDE -> operator = new DivideOperator<>(dataType);
            case MOD -> operator = new ModOperator<>(dataType);
        }
    }

    public void setFirstNumber (T firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber (T secondNumber) {
        this.secondNumber = secondNumber;
    }

    public T getFirstNumber () {
        return firstNumber;
    }

    public T getSecondNumber () {
        return secondNumber;
    }

    // 연산하기 + 결과 리스트에 저장
    // Divide by 0 예외처리: ArithmeticException
    public void calculate() {
        T result;
        try {
            // Enum과 추상 클래스를 이용한 다형성
            result = operator.operate(firstNumber, secondNumber);
            addResult((Double)result);
        } catch (ArithmeticException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }
}