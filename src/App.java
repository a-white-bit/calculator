import java.util.Scanner;

public class App {

    static Scanner sc;
    static boolean exit;
    // calculateType 타입별 상수
    static final byte INPUT_TYPE_ARITHMETIC = 11;
    static final byte INPUT_TYPE_CIRCLE = 12;

    /* 유형 선택
    ** 1. 사칙연산  2. 원의 넓이
     */
    static byte calculateTypeRequest() {
        while (true) {
            System.out.print("[계산 방식을 선택하세요] (1:사칙연산, 2:원의 넓이): ");
            switch (sc.nextLine()) {
                case "1": return INPUT_TYPE_ARITHMETIC;
                case "2": return INPUT_TYPE_CIRCLE;
            }
        }
    }
    static void inputFirstNumberRequest(ArithmeticCalculator calculator) {
        while (true) {
            System.out.print("[첫 번째 숫자를 입력하세요]: ");
            try {
                calculator.setFirstNumber(Double.parseDouble(sc.nextLine()));
                return;
            } catch (NumberFormatException e) {
                System.out.println("[에러]: 잘못된 입력입니다.");
            }
        }
    }
    static void inputSecondNumberRequest(ArithmeticCalculator calculator) {
        while (true) {
            System.out.print("[두 번째 숫자를 입력하세요]: ");
            try {
                calculator.setSecondNumber(Double.parseDouble(sc.nextLine()));
                return;
            } catch (NumberFormatException e) {
                System.out.println("[에러]: 잘못된 입력입니다.");
            }
        }
    }
    static void inputRadiusNumberRequest(CircleCalculator calculator) {
        while (true) {
            System.out.print("[원의 반지름을 입력하세요]: ");
            try {
                calculator.setRadius(Double.parseDouble(sc.nextLine()));
                return;
            } catch (NumberFormatException e) {
                System.out.println("[에러]: 잘못된 입력입니다.");
            }
        }
    }
    /*
    ** 연산기호(+ - * /) 입력받는 코드
    ** OperatorType: enum { ADD, SUBTRACT, MULTIPLY, DIVIDE, MOD }
     */
    static void inputOperatorRequest(ArithmeticCalculator calculator) {
        while (true) {
            System.out.print("[사칙연산 기호를 입력하세요]: ");
            try {
                calculator.setOperator(OperatorType.fromSymbol(sc.nextLine()));
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("[에러]: " + e.getMessage());
            }
        }
    }

    static boolean calculateByType(Calculator calculator) {
        try {
            calculator.calculate();
            return true;
        } catch (ArithmeticException e) {
            System.out.println("[계산 실패]: " + e.getMessage());
            return false;
        }
    }

    static void printResult(Calculator calculator) {
        System.out.println("[연산 결과]: " + Math.round(calculator.getLastResult() * 10000) / 10000.0);
    }

    static void printCircleResult(CircleCalculator calculator) {
        System.out.println("[원의 넓이]: " + Math.round(calculator.getLastResult() * 10000) / 10000.0 + " (r = " + calculator.getRadius() + ")");
    }

    /*
    ** 연산 리스트 삭제 질문
    ** 리스트가 비어있지 않을 경우에 삭제 진행
     */
    static void removeFirstResultRequest(Calculator calculator) {
        System.out.print("[가장 먼저 저장된 연산 결과를 삭제하시겠습니까?] (remove 입력 시 삭제) ");
        if ("remove".equals(sc.nextLine())) {
            try {
                calculator.removeFirstResult();
                System.out.println("[삭제 성공]");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("[삭제 실패]: " + e.getMessage());
            }
        }
    }

    /*
    ** 조회 여부 질문
     */
    static void printResultsRequest(Calculator calculator) {
        System.out.print("[저장된 연산결과를 조회하시겠습니까?] (inquiry 입력 시 조회) ");
        if ("inquiry".equals(sc.nextLine())) {
            System.out.print("[연산 리스트]: ");
            calculator.inquiryResults();
        }
    }

    /*
    ** 기준값 입력
    ** 연산결과 리스트에서 기준값과 비교하여 큰 값을 조회
     */
    static void resultsByStreamRequest(Calculator calculator) {
        System.out.print("[스트림 출력 테스트하시겠습니까?] (stream 입력) ");
        if ("stream".equals(sc.nextLine())) {
            try {
                System.out.print("[기준값] (기준보다 큰 값 출력): ");
                calculator.inquiryResultsByStream(Double.parseDouble(sc.nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.println("[출력 실패]: " + e.getMessage());
            }
        }
    }

    /*
    ** 종료 여부 질문
    ** '네' 또는 '아니요'가 아닐 시 다시 질문 ( return false )
     */
    static void terminateRequest() {
        while (true) {
            System.out.print("[다시 계산하시겠습니까?] (y/n) ");
            String scInput = sc.nextLine();
            if (scInput.equals("n")) {
                exit = true;
                return;
            } else if (scInput.equals("y")) {
                System.out.println();
                return;
            }
        }
    }

    public static void main(String[] args) {

        // 사칙연산, 원의 넓이
        // 인스턴스 생성
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator();
        CircleCalculator circleCalculator = new CircleCalculator();
        Calculator parentCalculator = null;

        sc = new Scanner(System.in);
        byte calculateType;

        // 사용자가 종료 요청 시 까지 반복
        while (!exit) {
            calculateType = calculateTypeRequest();
            if (calculateType == INPUT_TYPE_ARITHMETIC) {

                inputFirstNumberRequest(arithmeticCalculator);
                inputSecondNumberRequest(arithmeticCalculator);
                inputOperatorRequest(arithmeticCalculator);
                if (!calculateByType(arithmeticCalculator)) { terminateRequest(); continue; }
                printResult(arithmeticCalculator);
                parentCalculator = arithmeticCalculator;

            } else if (calculateType == INPUT_TYPE_CIRCLE) {

                inputRadiusNumberRequest(circleCalculator);
                if (!calculateByType(circleCalculator)) { terminateRequest(); continue; }
                printCircleResult(circleCalculator);
                parentCalculator = circleCalculator;

            }
            removeFirstResultRequest(parentCalculator);
            printResultsRequest(parentCalculator);
            resultsByStreamRequest(parentCalculator);
            terminateRequest();
        }
        System.out.println("[연산 종료]");
        sc.close();
    }
}