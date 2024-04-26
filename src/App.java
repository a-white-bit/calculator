import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // 입력받을 객체(Scanner) 선언
        Scanner sc = new Scanner(System.in);

        // 정수 타입 중 가장 큰 수 표현이 가능한 long을 채택
        long num1, num2;
        // 사칙연산 문자 1개
        char operator;

        // 두 정수를 입력받는 코드
        // 정수가 아닌 값(실수, 문자)을 받는 예외처리 코드 나중에 필요 (예외 발생)
        // *문제에서 양수를 입력하라고 명시하였지만, 음수를 입력해도 로직 문제가 없으므로 이대로 진행
        System.out.print("첫 번째 숫자를 입력하세요: ");
        num1 = sc.nextLong();
        System.out.print("두 번째 숫자를 입력하세요: ");
        num2 = sc.nextLong();

        // 연산기호(+ - * /) 입력받는 코드
        // 나중에 이상한 값이 들어올 때 예외처리 필요
        // charAt(0)으로 첫 번째 문자만 저장됨
        System.out.print("사칙연산 기호를 입력하세요: ");
        operator = sc.next().charAt(0);

        // 결과
        System.out.print("첫 번째 숫자: " + num1 + ", 두 번째 숫자: " + num2 + ", 연산기호: " + operator);

        sc.close();
    }
}