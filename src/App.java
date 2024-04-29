import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);                // 입력받을 객체(Scanner) 선언
        Calculator myCalc = new Calculator();               // 계산기 객체 생성
        int num1, num2;                                     // 정수 2개
        char operator;                                      // 사칙연산 문자 1개
        boolean error;                                      // 연산에러 여부
        boolean exit = false;                               // 종료 여부

        // 사용자가 종료 요청 시 까지 반복
        while (!exit) {
            // 초기화
            error = false;


            // 두 정수를 입력받는 코드
            // 정수가 아닌 값(실수, 문자)을 받는 예외처리 코드 나중에 필요 (예외 발생)
            System.out.print("[첫 번째 숫자를 입력하세요]: ");
            num1 = Integer.parseInt(sc.nextLine());
            System.out.print("[두 번째 숫자를 입력하세요]: ");
            num2 = Integer.parseInt(sc.nextLine());

            // 연산기호(+ - * /) 입력받는 코드
            // charAt(0)으로 첫 번째 문자만 저장됨
            System.out.print("[사칙연산 기호를 입력하세요]: ");
            operator = sc.nextLine().charAt(0);


            // 계산 시도
            try {
                myCalc.calculate(num1, num2, operator);
            } catch (Exception e) {
                System.out.println("[계산 실패]: " + e.getMessage());
                error = true;
            }


            // 결과 출력
            // 나중에 예외처리 - 메세지 유연하게 하기
            if (!error) {
                System.out.println("[연산 결과]: " + num1 + " " + operator + " " + num2 + " = " + myCalc.getLastResult());
            }


            // 연산 리스트 삭제 질문
            // 리스트가 비어있지 않을 경우에 삭제 진행
            // 비어 있다면 Exception
            System.out.print("[가장 먼저 저장된 연산 결과를 삭제하시겠습니까?] (remove 입력 시 삭제) ");
            if (sc.nextLine().equals("remove")) {
                try {
                    myCalc.removeFirstResult();
                } catch (Exception e) {
                    System.out.println("[삭제 실패]: " + e.getMessage());
                }
            }


            // 조회 여부 질문
            System.out.print("[저장된 연산결과를 조회하시겠습니까?] (inquiry 입력 시 조회) ");
            if (sc.nextLine().equals("inquiry")) {
                myCalc.inquiryResults();
            }


            // 종료 여부 질문
            // '네' 또는 '아니요'가 아닐 시 다시 질문
            while (true) {
                System.out.print("[다시 계산하시겠습니까?] (y/n) ");
                String exitQuestion = sc.nextLine();
                if (exitQuestion.equals("n")) {
                    exit = true;
                    break;
                } else if (exitQuestion.equals("y")) {
                    System.out.println();
                    break;
                }
            }
        }
        System.out.println("[연산 종료]");
        sc.close();
    }
}