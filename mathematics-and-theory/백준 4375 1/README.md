# 1

> https://www.acmicpc.net/problem/4375

## 문제 설명

- 문제

2와 5로 나누어 떨어지지 않는 정수 n(1 ≤ n ≤ 10000)가 주어졌을 때, 각 자릿수가 모두 1로만 이루어진 n의 배수를 찾는 프로그램을 작성하시오.

- 입력

입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있고, n이 주어진다.

- 출력

각 자릿수가 모두 1로만 이루어진 n의 배수 중 가장 작은 수의 자리수를 출력한다.

## 접근 방법

다음 입력값이 null일 때까지 입력 받기.  
자릿수가 커질 때마다 모듈러 연산을 통해, 나머지 연산.

## 문제 해결 과정

- 첫 번째 시도:
    - 1, 11, 111, ... 이런 식으로 증가하는 `tmp` 값을 계속 증가시켜 `tmp % n == 0`이 될 때까지 카운팅.
        - 단순한 연산 자체는 시간 복잡도 O(1)라고 생각해 while문을 반복했으나 시간 초과.
        - int 또는 long 타입을 넘어서는 BigInteger 같은 수의 범위로 넘어가면 O(N * M)을 가질 수 있으며, N과 M은 두 숫자의 비트 길이.
- 최종 해결 방법:
    - `tmp`의 자릿수를 증가시키는 연산 마다, 모듈려 연산을 하며 카운팅.
        - 한편 `tmp`는 자릿수를 증가시킬 때마다 `tmp * 10 + 1`의 값이므로 모듈러 연산을 앞에서부터 진행해보면,
        - `(tmp * 10 + 1) % n` = `((tmp * 10) % n + 1 % n) % n`. 여기서 바깥의 n은 생략할 수 있음.
            - 덧셈의 좌항은 `(tmp * 10) % n`에서 이미 n으로 나눈 나머지, 우항은 `1 % n`으로 그냥 1이므로.
        - 따라서 `tmp`의 연산 값은 `tmp = (tmp * 10) % n + 1`만 남게 된다.
- 25.1.19. 다시 푼 방법:
  - 모듈러 연산을 사용하여, `tmp`의 자릿수를 증가시키며 계산. 동일한 방법 사용.


## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = "";
        int n;
        while ((s = br.readLine()) != null) {
            n = Integer.parseInt(s);
            long tmp = 1;
            int cnt = 1;
            while (true) {
                if (tmp % n == 0) {
                    sb.append(cnt).append(" ");
                    break;
                }
                tmp = (tmp * 10 + 1) % n;
                cnt++;
            }
        }
        System.out.println(sb);
    }
}
```