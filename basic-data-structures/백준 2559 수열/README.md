# 수열

> https://www.acmicpc.net/problem/2559

## 문제 설명

- 문제

매일 아침 9시에 학교에서 측정한 온도가 어떤 정수의 수열로 주어졌을 때, 연속적인 며칠 동안의 온도의 합이 가장 큰 값을 알아보고자 한다.

예를 들어, 아래와 같이 10일 간의 온도가 주어졌을 때,

3 -2 -4 -9 0 3 7 13 8 -3

모든 연속적인 이틀간의 온도의 합은 아래와 같다.

이때, 온도의 합이 가장 큰 값은 21이다.

또 다른 예로 위와 같은 온도가 주어졌을 때, 모든 연속적인 5일 간의 온도의 합은 아래와 같으며,

이때, 온도의 합이 가장 큰 값은 31이다.

매일 측정한 온도가 정수의 수열로 주어졌을 때, 연속적인 며칠 동안의 온도의 합이 가장 큰 값을 계산하는 프로그램을 작성하시오.

- 입력

첫째 줄에는 두 개의 정수 N과 K가 한 개의 공백을 사이에 두고 순서대로 주어진다. 첫 번째 정수 N은 온도를 측정한 전체 날짜의 수이다. N은 2 이상 100,000 이하이다. 두 번째 정수 K는 합을 구하기
위한 연속적인 날짜의 수이다. K는 1과 N 사이의 정수이다. 둘째 줄에는 매일 측정한 온도를 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다. 이 수들은 모두 -100 이상 100 이하이다.

- 출력

첫째 줄에는 입력되는 온도의 수열에서 연속적인 K일의 온도의 합이 최대가 되는 값을 출력한다.

## 접근 방법

연속적인 K일의 온도의 합 중 최댓값이므로, 슬라이딩 윈도우를 사용해 풀이.  
첫 번째 윈도우의 합을 초기값으로 설정한 뒤, 윈도우를 이동하며 최댓값 갱신.

## 문제 해결 과정

- 첫 번째 시도:
    - 슬라이딩 윈도우를 사용했지만, 로직 구현 상의 문제로 마지막 윈도우에서 최댓값인 경우를 갱신하지 않아 실패.
- 최종 해결 방법:
    - 처음 K개의 온도 합을 `ans` 초기값으로 설정.
    - 이후 끝 인덱스까지 윈도우를 이동하며, 새 인덱스 값을 더하고 좌측 끝 인덱스를 빼기.
        - 새로 갱신된 `tmp` 값으로 `ans` 갱신.
- 다른 해결 방법:
    - 거의 유사하긴 하지만, 구간합을 이용하여 풀이.
    - `int[] pSum`에 누적합을 저장한 뒤, 인덱스 K부터 N까지 구간합을 계산하고, 최댓값 갱신.

## 다른 코드

```java
public class Solution {

    static int N, K, ans = Integer.MIN_VALUE;
    static int[] arr, pSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        K = Integer.parseInt(tok[1]);
        arr = new int[N];
        pSum = new int[N + 1];
        tok = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tok[i]);
            pSum[i + 1] = pSum[i] + arr[i];
        }

        for (int i = K; i < N + 1; i++) {
            ans = Math.max(ans, pSum[i] - pSum[i - K]);
        }
        System.out.println(ans);
    }
}
```