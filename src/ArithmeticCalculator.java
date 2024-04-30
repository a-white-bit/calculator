public class ArithmeticCalculator extends Calculator {

    private OperatorType operatorType;  // Enum 클래스를 활용
    private AbstractOperator operator;  // 추상 클래스를 활용
    private double firstNumber;
    private double secondNumber;


    // Enum 클래스를 활용한 다형성
    // 매개변수: enum class {ADD, SUBTRACT, MULTIPLY, DIVIDE, MOD}
    public void setOperator (OperatorType type) {
        operatorType = type;

        // 추상 클래스를 이용한 다형성 (변경 전)
        // 매개변수: AbstractOperator 추상클래스 <-> 자식클래스 {AddOperator, ..} 간 자동형변환을 활용
        //this.operator = operator;
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
            // Enum 클래스를 이용한 다형성
            result = operatorType.operate(firstNumber, secondNumber);

            // 추상 클래스를 이용한 다형성 (변경 전)
            //result = operator.operate(firstNumber, secondNumber);

            addResult(result);
        } catch (ArithmeticException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }
}