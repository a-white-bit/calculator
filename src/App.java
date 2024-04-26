import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // 입력받을 객체(Scanner) 선언
        Scanner sc = new Scanner(System.in);

        long num1, num2;                // 정수 타입 중 가장 큰 수 표현이 가능한 long을 채택
        char operator;                  // 사칙연산 문자 1개
        double result;                  // 연산 결과값
        boolean error;                  // 연산에러 여부
        boolean exit = false;           // 종료 여부

        // 사용자가 종료 요청 시 까지 반복
        while (!exit) {
            // 변수 초기화
            result = 0;
            error = false;

            // 두 정수를 입력받는 코드
            // 정수가 아닌 값(실수, 문자)을 받는 예외처리 코드 나중에 필요 (예외 발생)
            // *문제에서 양수를 입력하라고 명시하였지만, 음수를 입력해도 로직 문제가 없으므로 이대로 진행
            System.out.print("첫 번째 숫자를 입력하세요: ");
            num1 = Long.parseLong(sc.nextLine());
            System.out.print("두 번째 숫자를 입력하세요: ");
            num2 = Long.parseLong(sc.nextLine());

            // 연산기호(+ - * /) 입력받는 코드
            // 사칙연산이 아닌 값이 들어올 때 예외처리 필요 (언제? 어디서?)
            // charAt(0)으로 첫 번째 문자만 저장됨
            System.out.print("사칙연산 기호를 입력하세요: ");
            operator = sc.nextLine().charAt(0);

            // 연산하기
            // 사칙연산이 아닌 값이 들어올 때 예외처리 필요 (언제? 어디서?)
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> {
                    if (num2 == 0) error = true;
                    else result = (double)num1 / (double)num2;  // 강제 형변환 필요
                }
            }

            // 결과 출력
            // 나중에 예외처리 - 메세지 유연하게 하기
            if (error) System.out.println("나눗셈 연산에서 두번째 정수가 0이 될 수 없습니다.");
            else {
                System.out.println("--첫 번째 숫자: " + num1 + ", 두 번째 숫자: " + num2 + ", 연산기호: " + operator);
                System.out.println("--연산 결과: " + result);
            }


            // 종료 여부 질문 '네' 또는 '아니요'가 아닐 시 다시 질문
            // ...'네' 입력시 처음부터 반복
            // ...'아니오' 입력시 종료
            while (true) {
                System.out.println("다시 계산하시겠습니까? (네/아니요)");
                String exitQuestion = sc.nextLine();
                System.out.println(exitQuestion);
                if (exitQuestion.equals("아니요")) {
                    exit = true;
                    break;
                }
                else if (exitQuestion.equals("네")) break;
            }
        }
        sc.close();
    }
}