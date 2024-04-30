import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // 사칙연산, 원의 넓이 각각 인스턴스 생성
        // aCalc와 cCalc를 합칠 수 없을까?
        ArithmeticCalculator aCalc = new ArithmeticCalculator();
        CircleCalculator cCalc = new CircleCalculator();

        Scanner sc = new Scanner(System.in);
        String scInput;                         // Scanner로 받을 문자열
        double radius;
        boolean exit = false;

        // 사용자가 종료 요청 시 까지 반복
        while (!exit) {
            // 사칙연산 or 원의 넓이를 구할지 선택
            do {
                System.out.print("[계산 방식을 선택하세요] (1:사칙연산, 2:원의 넓이): ");
                scInput = sc.nextLine();
            } while (!scInput.equals("1") && !scInput.equals("2"));

            switch (scInput) {
                case "1":  // 사칙연산 실행부

                    // 두 정수를 입력받는 코드
                    // 실수가 아닌 값을 받으면 반복
                    while (true) {
                        System.out.print("[첫 번째 숫자를 입력하세요]: ");
                        scInput = sc.nextLine();
                        try {
                            aCalc.setFirstNumber(Double.parseDouble(scInput));
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("[에러]: 잘못된 입력입니다.");
                        }
                    }
                    while (true) {
                        System.out.print("[두 번째 숫자를 입력하세요]: ");
                        scInput = sc.nextLine();
                        try {
                            aCalc.setSecondNumber(Double.parseDouble(scInput));
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("[에러]: 잘못된 입력입니다.");
                        }
                    }

                    // 연산기호(+ - * /) 입력받는 코드
                    // 잘못된 연산기호면 반복
                    while (true) {
                        System.out.print("[사칙연산 기호를 입력하세요]: ");
                        scInput = sc.nextLine();
                        switch (scInput) {
                            case "+" -> aCalc.setOperator(new AddOperator());
                            case "-" -> aCalc.setOperator(new SubtractOperator());
                            case "*" -> aCalc.setOperator(new MultiplyOperator());
                            case "/" -> aCalc.setOperator(new DivideOperator());
                            default -> { System.out.println("[에러]: 잘못된 연산기호입니다."); continue; }
                        }
                        break;
                    }


                    // 계산
                    try {
                        aCalc.calculate();

                        // 결과 출력
                        try {
                            System.out.println("[연산 결과]: " + aCalc.getLastResult());
                        } catch (Exception e) {
                            System.out.println("[출력 실패]: " + e.getMessage());
                        }

                        // 연산 리스트 삭제 질문
                        // 리스트가 비어있지 않을 경우에 삭제 진행
                        System.out.print("[가장 먼저 저장된 연산 결과를 삭제하시겠습니까?] (remove 입력 시 삭제) ");
                        if (sc.nextLine().equals("remove")) {
                            try {
                                aCalc.removeFirstResult();
                            } catch (Exception e) {
                                System.out.println("[삭제 실패]: " + e.getMessage());
                            }
                        }

                        // 조회 여부 질문
                        System.out.print("[저장된 연산결과를 조회하시겠습니까?] (inquiry 입력 시 조회) ");
                        if (sc.nextLine().equals("inquiry")) {
                            System.out.print("[연산 리스트]: ");
                            aCalc.inquiryResults();
                        }

                    } catch (Exception e) {
                        System.out.println("[계산 실패]: " + e.getMessage());
                    }
                    break;

                case "2":  // 원의 넓이 실행부
                    // 값 입력
                    // 실수가 아닌 값을 받는 예외처리 코드 나중에 필요 (예외 발생)
                    System.out.print("[원의 반지름을 입력하세요]: ");
                    scInput = sc.nextLine();

                    // setter & calculate
                    cCalc.setRadius(Double.parseDouble(scInput));
                    cCalc.calculate();

                    // 출력
                    try {
                        System.out.println("[원의 넓이]: " + cCalc.getLastResult() + " (r = " + cCalc.getRadius() + ")");
                    } catch (Exception e) {
                        System.out.println("[출력 실패]: " + e.getMessage());
                    }

                    // 삭제 여부
                    System.out.print("[가장 먼저 저장된 연산 결과를 삭제하시겠습니까?] (remove 입력 시 삭제) ");
                    if (sc.nextLine().equals("remove")) {
                        try {
                            cCalc.removeFirstResult();
                        } catch (Exception e) {
                            System.out.println("[삭제 실패]: " + e.getMessage());
                        }
                    }

                    // 조회 여부
                    System.out.print("[저장된 연산결과를 조회하시겠습니까?] (inquiry 입력 시 조회) ");
                    if (sc.nextLine().equals("inquiry")) {
                        cCalc.inquiryResults();
                    }
                    break;
            }


            // 종료 여부 질문
            // '네' 또는 '아니요'가 아닐 시 다시 질문
            while (true) {
                System.out.print("[다시 계산하시겠습니까?] (y/n) ");
                scInput = sc.nextLine();
                if (scInput.equals("n")) {
                    exit = true;
                    break;
                } else if (scInput.equals("y")) {
                    System.out.println();
                    break;
                }
            }
        }
        System.out.println("[연산 종료]");
        sc.close();
    }
}