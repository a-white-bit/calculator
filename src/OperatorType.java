public enum OperatorType {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MOD("%");

    private final String symbol;

    // 생성자(싱글톤)
    private OperatorType(String s) {
        this.symbol = s;
    }

    public String getSymbol() {
        return symbol;
    }

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