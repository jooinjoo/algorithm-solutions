# 트럭 주차

> https://www.acmicpc.net/problem/2979

## 문제 설명

- 문제

상근이는 트럭을 총 세 대 가지고 있다. 오늘은 트럭을 주차하는데 비용이 얼마나 필요한지 알아보려고 한다.

상근이가 이용하는 주차장은 주차하는 트럭의 수에 따라서 주차 요금을 할인해 준다.

트럭을 한 대 주차할 때는 1분에 한 대당 A원을 내야 한다. 두 대를 주차할 때는 1분에 한 대당 B원, 세 대를 주차할 때는 1분에 한 대당 C원을 내야 한다.

A, B, C가 주어지고, 상근이의 트럭이 주차장에 주차된 시간이 주어졌을 때, 주차 요금으로 얼마를 내야 하는지 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 문제에서 설명한 주차 요금 A, B, C가 주어진다. (1 ≤ C ≤ B ≤ A ≤ 100)

다음 세 개 줄에는 두 정수가 주어진다. 이 정수는 상근이가 가지고 있는 트럭이 주차장에 도착한 시간과 주차장에서 떠난 시간이다. 도착한 시간은 항상 떠난 시간보다 앞선다. 입력으로 주어지는 시간은 1과 100사이
이다.

- 출력

첫째 줄에 상근이가 내야하는 주차 요금을 출력한다.

## 접근 방법

시간의 흐름을 배열에 펼쳐놓은 뒤, 트럭의 주차를 해당 시간에 카운팅한다.  
이를 통해 각 시간엔 0~3까지 존재하며, 루프하여 각 시간 수에 A,B,C를 계산하면 된다.  
시간 범위는 시작 시간 이상, 종료 시간 미만으로 잡는다.

## 문제 해결 과정

- 최종 해결 방법:
    - `int[] cnt` 배열의 각 인덱스는 해당 시간에 존재하는 트럭의 수다.
    - 따라서 각 트럭 별로 시작 시간, 종료 시간에 따라 `cnt` 배열에 주차된 인덱스 시간에 카운팅.
- 25.6.24. 다시 푼 방법:
    - `int[] cnt` 배열에 각 시간마다 존재하는 트럭 수 카운팅.
        - 주차한 시간, 즉 시작 시간의 바로 다음 시간부터 카운팅하는 것이 핵심. 시작 시간 + 1 ~ 끝 시간까지 카운팅한다.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[] cnt = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        for (int i = 0; i < 3; i++) {
            input = br.readLine().split(" ");
            int lt = Integer.parseInt(input[0]) + 1;
            int rt = Integer.parseInt(input[1]);
            for (int j = lt; j <= rt; j++) cnt[j]++;
        }

        int ans = 0;
        for (int i : cnt) {
            if (i == 1) ans += a;
            else if (i == 2) ans += 2 * b;
            else if (i == 3) ans += 3 * c;
        }
        System.out.println(ans);
    }
}
```