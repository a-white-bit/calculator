public class DivideOperator<T extends Number> extends AbstractOperator<T> {
    public final Class<T> type;
    public DivideOperator(Class<T> type) { this.type = type; }
    public T operate(T a, T b) {
        if (b.doubleValue() == 0) { throw new ArithmeticException("0으로 나눌 수 없습니다."); }
        else {
            double result = a.doubleValue() / b.doubleValue();
            return NumberConversion.convertNumberToType(result, type);
        }
    }
}