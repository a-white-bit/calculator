public class SubtractOperator<T extends Number> extends AbstractOperator<T> {
    public final Class<T> type;
    public SubtractOperator(Class<T> type) { this.type = type; }
    public T operate(T a, T b) {
        double result = a.doubleValue() - b.doubleValue();
        return NumberConversion.convertNumberToType(result, type);
    }
}
