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
        System.out.print("[계산 방식을 선택하세요] (1:사칙연산, 2:원의 넓이): ");
        byte inputType = 0;
        switch (sc.nextLine()) {
            case "1" -> { inputType = INPUT_TYPE_ARITHMETIC; }
            case "2" -> { inputType = INPUT_TYPE_CIRCLE; }
        }
        return inputType;
    }
    static boolean inputFirstNumberRequest(ArithmeticCalculator calculator) {
            System.out.print("[첫 번째 숫자를 입력하세요]: ");
            try {
                calculator.setFirstNumber(Double.parseDouble(sc.nextLine()));
                return true;
            } catch (NumberFormatException e) {
                System.out.println("[에러]: 잘못된 입력입니다.");
                return false;
            }
    }
    static boolean inputSecondNumberRequest(ArithmeticCalculator calculator) {
            System.out.print("[두 번째 숫자를 입력하세요]: ");
            try {
                calculator.setSecondNumber(Double.parseDouble(sc.nextLine()));
                return true;
            } catch (NumberFormatException e) {
                System.out.println("[에러]: 잘못된 입력입니다.");
                return false;
            }
    }
    static boolean inputRadiusNumberRequest(CircleCalculator calculator) {
        System.out.print("[원의 반지름을 입력하세요]: ");
        try {
            calculator.setRadius(Double.parseDouble(sc.nextLine()));
            return true;
        } catch (NumberFormatException e) {
            System.out.println("[에러]: 잘못된 입력입니다.");
            return false;
        }
    }
    /*
    ** 연산기호(+ - * /) 입력받는 코드
    ** OperatorType: enum { ADD, SUBTRACT, MULTIPLY, DIVIDE, MOD }
     */
    static boolean inputOperatorRequest(ArithmeticCalculator calculator) {
        System.out.print("[사칙연산 기호를 입력하세요]: ");
        try {
            calculator.setOperator(OperatorType.fromSymbol(sc.nextLine()));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[에러]: " + e.getMessage());
            return false;
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

    static boolean printResult(Calculator calculator) {
        try {
            System.out.println("[연산 결과]: " + calculator.getLastResult());
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[출력 실패]: " + e.getMessage());
            return false;
        }
    }

    static boolean printCircleResult(CircleCalculator calculator) {
        try {
            System.out.println("[원의 넓이]: " + calculator.getLastResult() + " (r = " + calculator.getRadius() + ")");
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[출력 실패]: " + e.getMessage());
            return false;
        }
    }

    /*
    ** 연산 리스트 삭제 질문
    ** 리스트가 비어있지 않을 경우에 삭제 진행
     */
    static boolean removeFirstResultRequest(Calculator calculator) {
        System.out.print("[가장 먼저 저장된 연산 결과를 삭제하시겠습니까?] (remove 입력 시 삭제) ");
        if (sc.nextLine().equals("remove")) {
            try {
                calculator.removeFirstResult();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("[삭제 실패]: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /*
    ** 조회 여부 질문
     */
    static boolean printResultsRequest(Calculator calculator) {
        System.out.print("[저장된 연산결과를 조회하시겠습니까?] (inquiry 입력 시 조회) ");
        if (sc.nextLine().equals("inquiry")) {
            System.out.print("[연산 리스트]: ");
            calculator.inquiryResults();
        }
        // try-catch 로 변경
        return true;
    }

    /*
    ** 기준값 입력
    ** 연산결과 리스트에서 기준값과 비교하여 큰 값을 조회
     */
    static boolean resultsByStreamRequest(Calculator calculator) {
        System.out.print("[스트림 출력 테스트하시겠습니까?] (stream 입력) ");
        if (sc.nextLine().equals("stream")) {
            try {
                System.out.print("[기준값] (기준보다 큰 값 출력): ");
                calculator.inquiryResultsByStream(Double.parseDouble(sc.nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.println("[출력 실패]: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /*
    ** 종료 여부 질문
    ** '네' 또는 '아니요'가 아닐 시 다시 질문 ( return false )
     */
    static boolean terminateRequest() {
        System.out.print("[다시 계산하시겠습니까?] (y/n) ");
        String scInput = sc.nextLine();
        if (scInput.equals("n")) {
            exit = true;
            return true;
        } else if (scInput.equals("y")) {
            System.out.println();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        // 사칙연산, 원의 넓이 인스턴스 생성
        // (하고싶은 것 1. arithmeticCalculator + circleCalculator 합칠 수 없을까? -> 유연하게 만들고 싶음)
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator();
        CircleCalculator circleCalculator = new CircleCalculator();

        sc = new Scanner(System.in);
        byte calculateType;
        CalculateAppLevel requestLevel = CalculateAppLevel.START_APP;

        // if (requestLevel != CalculateAppLevel.START_APP) { exit = true; }

        // 사용자가 종료 요청 시 까지 반복
        while (!exit) {
            calculateType = calculateTypeRequest();
            if (calculateType == INPUT_TYPE_ARITHMETIC) {
                requestLevel = CalculateAppLevel.ARITHMATIC_INPUT_FIRSTNUMBER;
            }
            else if (calculateType == INPUT_TYPE_CIRCLE) {
                requestLevel = CalculateAppLevel.CIRCLE_INPUT_RADIUS;
            }


            while (requestLevel != CalculateAppLevel.TERMINATE_APP) {
                switch (requestLevel) {
                    case ARITHMATIC_INPUT_FIRSTNUMBER:
                        if (inputFirstNumberRequest(arithmeticCalculator)) {
                            requestLevel = CalculateAppLevel.ARITHMATIC_INPUT_SECONDNUMBER;
                        }
                        continue;
                    case ARITHMATIC_INPUT_SECONDNUMBER:
                        if (inputSecondNumberRequest(arithmeticCalculator)) {
                            requestLevel = CalculateAppLevel.ARITHMATIC_INPUT_OPERATOR;
                        }
                        continue;
                    case ARITHMATIC_INPUT_OPERATOR:
                        if (inputOperatorRequest(arithmeticCalculator)) {
                            requestLevel = CalculateAppLevel.CALCULATE_BY_TYPE;
                        }
                        continue;
                    case CIRCLE_INPUT_RADIUS:
                        if (inputRadiusNumberRequest(circleCalculator)) {
                            requestLevel = CalculateAppLevel.CALCULATE_BY_TYPE;
                        }
                        continue;
                    case CALCULATE_BY_TYPE :
                        switch (calculateType) {
                            case INPUT_TYPE_ARITHMETIC:
                                if (calculateByType(arithmeticCalculator)) {
                                    requestLevel = CalculateAppLevel.PRINT_RESULT;
                                } else {
                                    requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                } break;
                            case INPUT_TYPE_CIRCLE:
                                if (calculateByType(circleCalculator)) {
                                    requestLevel = CalculateAppLevel.PRINT_RESULT_CIRCLE;
                                } else {
                                    requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                } break;
                        }
                        continue;
                    case PRINT_RESULT:
                        if (printResult(arithmeticCalculator)) {
                            requestLevel = CalculateAppLevel.REMOVE_FIRST_RESULT_REQ;
                        }
                        continue;
                    case PRINT_RESULT_CIRCLE:
                        if (printCircleResult(circleCalculator)) {
                            requestLevel = CalculateAppLevel.REMOVE_FIRST_RESULT_REQ;
                        }
                        continue;
                    case REMOVE_FIRST_RESULT_REQ:
                            switch (calculateType) {
                                case INPUT_TYPE_ARITHMETIC:
                                    if (removeFirstResultRequest(arithmeticCalculator)) {
                                        requestLevel = CalculateAppLevel.PRINT_RESULTS_REQ;
                                    } else {
                                        requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                    } break;
                                case INPUT_TYPE_CIRCLE:
                                    if (removeFirstResultRequest(circleCalculator)) {
                                        requestLevel = CalculateAppLevel.PRINT_RESULTS_REQ;
                                    } else {
                                        requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                    } break;
                            }
                            continue;
                    case PRINT_RESULTS_REQ:
                        switch (calculateType) {
                            case INPUT_TYPE_ARITHMETIC:
                                if (printResultsRequest(arithmeticCalculator)) {
                                    requestLevel = CalculateAppLevel.RESULTS_BY_STREAM_REQ;
                                } else {
                                    requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                } break;
                            case INPUT_TYPE_CIRCLE:
                                if (printResultsRequest(circleCalculator)) {
                                    requestLevel = CalculateAppLevel.RESULTS_BY_STREAM_REQ;
                                } else {
                                    requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                } break;
                        }
                        continue;
                    case RESULTS_BY_STREAM_REQ:
                        switch (calculateType) {
                            case INPUT_TYPE_ARITHMETIC:
                                resultsByStreamRequest(arithmeticCalculator);
                                requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                break;
                            case INPUT_TYPE_CIRCLE:
                                resultsByStreamRequest(circleCalculator);
                                requestLevel = CalculateAppLevel.TERMINATE_REQ;
                                break;
                        }
                        continue;
                    case TERMINATE_REQ:
                        if (terminateRequest()) {
                            requestLevel = CalculateAppLevel.TERMINATE_APP;
                        }
                }
            }
        }

        System.out.println("[연산 종료]");
        sc.close();
    }
}