public class DivideOperator extends AbstractOperator {
    public double operate(double num1, double num2) {
        if (num2 == 0) { throw new ArithmeticException("0으로 나눌 수 없습니다."); }
        else return num1 / num2;
    }
}