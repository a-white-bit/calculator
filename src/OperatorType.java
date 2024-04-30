public enum OperatorType {
    ADD("+") {
        public double operate(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT("-") {
        public double operate(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY("*") {
        public double operate(double a, double b) {
            return a * b;
        }
    },
    DIVIDE("/") {
        public double operate(double a, double b) {
            if (b == 0) { throw new ArithmeticException("0으로 나눌 수 없습니다."); }
            else return a / b;
        }
    },
    MOD("%") {
        public double operate(double a, double b) {
            return a % b;
        }
    };

    private final String symbol;

    // 생성자(싱글톤)
    private OperatorType(String s) {
        this.symbol = s;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract double operate(double a, double b);

    // symbol(+ - * / %)로 enum 객체 구하기
    public static OperatorType fromSymbol(String symbol) {
        for (OperatorType type : OperatorType.values()) {
            if (type.getSymbol().equals(symbol)) {
                return type;
            }
        }
        throw new IllegalArgumentException("잘못된 연산기호입니다.");
    }
}