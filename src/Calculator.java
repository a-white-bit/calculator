import java.util.ArrayList;

abstract class Calculator {
    // 리스트 캡슐화 (private)
    // 리스트 참조 변경 불가 (final)
    private final ArrayList<Double> resultList;

    public Calculator() {
        resultList = new ArrayList<>();
    }

    abstract void calculate();

    // 마지막 결과값 구하기
    public double getLastResult() throws IndexOutOfBoundsException {
        if (resultList.isEmpty()) throw new IndexOutOfBoundsException("(빈 리스트)");
        else return resultList.get(resultList.size() - 1);
    }

    // 인덱스 접근
    // 잘못된 인덱스 접근 예외 처리 필요함
    public double getResultByIndex(int index) {
        return resultList.get(index);
    }

    // 요소 개수 구하기
    public int getNumberOfResult() {
        return resultList.size();
    }
    
    // 리스트 전부 출력
    public void inquiryResults() {
        if (resultList.isEmpty())
            System.out.print("(빈 리스트)");
        else
            resultList.forEach(result -> System.out.print(result + " "));

        System.out.println();
    }

    // 리스트 가장 오래된 요소 삭제
    public void removeFirstResult() throws Exception {
        if (resultList.isEmpty()) throw new Exception("(빈 리스트)");
        else resultList.remove(0);
    }

    // 결과값을 리스트에 요소 추가
    void addResult(double result) {
        resultList.add(result);
    }

}