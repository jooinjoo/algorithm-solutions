# 곱셈

> https://www.acmicpc.net/problem/1629

## 문제 설명

- 문제

자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.

- 출력

첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.

## 접근 방법

함수의 호출 횟수를 줄이기 위해, b = 1이 될 때 까지 (b / 2) 재귀.  
곱셈마다 모듈러 연산을 활용해 나머지 출력.

## 문제 해결 과정

- 첫 번째 시도:
    - 모듈러 연산은 사용했으나, 단순히 for문을 통해 b번 반복하여 나머지 구하기.
        - `a, b, c`가 약 21억, 즉 int 값의 최대에 해당하는 만큼 당연히 시간 초과.
- 최종 해결 방법:
    - 같은 값을 반복해서 곱하는 지수의 특성을 활용하여, 지수를 반씩 나누며 시간복잡도 O(logN) 해결 가능.
        - 예를 들어, 2^20 = 2^10 * 2^10 = 2^5 * 2^5 * 2^5 * 2^5 ... 이런 식으로 반복 횟수를 줄여나갈 수 있다.
        - 한편 현재 `b`가 홀수인 경우에는, 한번 더 `a`를 곱해주며 연산하도록 설정. ex) 2^5 = 2^2 * 2^2 * 2
    - 또한 수를 곱하다보면 일시적으로 int 범위보다 커지기에 long 타입을 사용.
- 25.7.10. 다시 푼 방법:
    - 분할 정복을 이용하여, B가 1이 될 때까지 2로 나누면서 반복 횟수를 줄이기.
        - 단순히 B번 반복한다면 시간 제한이 0.5초로 시간 초과가 날 수밖에 없다.(B가 21억 이하로 반복 횟수가 시간 제한에 걸리기 때문)
    - 또한 모듈러 연산을 활용해 현재 B가 짝수인지 홀수인지에 따라 다르게 처리.


## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        C = Integer.parseInt(input[2]);

        System.out.println(recur(A, B, C));
    }

    static long recur(int A, int B, int C) {
        if (B == 1) return A % C;

        long tmp = recur(A, B / 2, C);
        if (B % 2 == 1) {
            return (tmp * tmp % C) * A % C;
        } else {
            return tmp * tmp % C;
        }
    }
}
```
