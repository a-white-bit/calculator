import java.util.ArrayList;

public class Calculator {
    // 리스트 캡슐화 (private)
    // 리스트 참조 변경 불가 (final)
    private final ArrayList<Double> resultList;

    public Calculator() {
        resultList = new ArrayList<Double>();
    }

    // 연산하기
    // 사칙연산이 아닌 값이 들어올 때 예외처리
    // Divide by 0 예외처리
    public void calculate(int num1, int num2, char operator) throws Exception {
        double result = 0;
        switch (operator) {
            case '+' -> result = (double)num1 + num2;
            case '-' -> result = (double)num1 - num2;
            case '*' -> result = (double)num1 * num2;
            case '/' -> {
                if (num2 == 0) throw new Exception("0으로 나눌 수 없습니다.");
                else result = (double)num1 / num2;
            }
            default -> throw new Exception("잘못된 연산기호를 입력하셨습니다.");
        }
        // 리스트에 결과 저장
        resultList.add(result);
    }

    // getter (1)
    // List 잘못된 인덱스 접근 예외 처리 필요함
    public double getLastResult() throws IndexOutOfBoundsException {
        if (resultList.isEmpty()) throw new IndexOutOfBoundsException("빈 리스트입니다.");
        else return resultList.get(resultList.size() - 1);
    }
    // getter (2)
    // List 잘못된 인덱스 접근 예외 처리 필요함
    public double getListByIndex(int index) {
        return resultList.get(index);
    }
    // getter (3)
    public int getNumberOfList() {
        return resultList.size();
    }

    // getter (4) - 리스트 전부 출력
    public void inquiryResults() {
        if (resultList.isEmpty())
            System.out.print("[연산 결과]: (빈 리스트)");
        else
            resultList.forEach(result -> System.out.print(result + " "));
        System.out.println();
    }


    // setter (1) - 리스트 가장 오래된 요소 삭제 + get
    // 잘못된 인덱스 접근 예외 처리 필요
    public void removeFirstResult() throws Exception {
        if (resultList.isEmpty()) throw new Exception("빈 리스트입니다.");
        else resultList.remove(0);
    }

    // setter
    // 리스트에 값을 넣는 것은 calculate 외에 허용하지 않음

}