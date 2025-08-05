# NBA 농구

> https://www.acmicpc.net/problem/2852

## 문제 설명

- 문제

동혁이는 NBA 농구 경기를 즐겨 본다. 동혁이는 골이 들어갈 때 마다 골이 들어간 시간과 팀을 적는 이상한 취미를 가지고 있다.

농구 경기는 정확히 48분동안 진행된다. 각 팀이 몇 분동안 이기고 있었는지 출력하는 프로그램을 작성하시오.

- 입력

첫째 줄에 골이 들어간 횟수 N(1<=N<=100)이 주어진다. 둘째 줄부터 N개의 줄에 득점 정보가 주어진다. 득점 정보는 득점한 팀의 번호와 득점한 시간으로 이루어져 있다. 팀 번호는 1 또는 2이다. 득점한
시간은 MM:SS(분:초) 형식이며, 분과 초가 한자리 일 경우 첫째자리가 0이다. 분은 0보다 크거나 같고, 47보다 작거나 같으며, 초는 0보다 크거나 같고, 59보다 작거나 같다. 득점 시간이 겹치는 경우는
없다.

- 출력

첫째 줄에 1번 팀이 이기고 있던 시간, 둘째 줄에 2번 팀이 이기고 있던 시간을 출력한다. 시간은 입력과 같은 형식(MM:SS)으로 출력한다.

## 접근 방법

골이 들어갈 때마다, 직전 득점 시간 `pre`와 현재 득점 시간 `cur`의 차이를 이기고 있던 팀에게 누적.  
이후 새로운 득점 현황과 직전 득점 시간을 갱신.  
모든 득점이 끝난 이후부터 경기 종료까지의 득점 시간도 갱신.

## 문제 해결 과정

- 최종 해결 방법:
    - 직전 득점 시간 `pre`를 득점마다 갱신하며, 동시에 1번 팀과 2번 팀의 누적 시간을 각각 갱신.
    - 새로운 득점이 입력될 때마다 직전 상황을 업데이트.
        - 1번 팀과 2번 팀의 점수 `s1`, `s2`가 같지 않았다면, 더 높은 쪽에게 현재 시간 `cur`과 `pre`의 차이를 누적.
        - 이 과정에서 모든 시간을 초 기준으로 변환. 따라서 시간의 최대값은 48분, 즉 `2880`.
    - 누적 시간을 조정했다면, 새로운 득점에 대해 1번 팀과 2번 팀의 점수, 그리고 직전 시간 `pre`도 현재 시간으로 갱신.
        - 모든 득점이 끝난 이후에도 둘의 점수가 다르다면, 종료까지 리드한 것이므로 시간 누적 필요.
    - 1번 팀과 2번 팀 각각의 누적 시간 `cnt1`, `cnt2`도 `System.out.printf()`를 활용해 (MM:SS)로 출력.
        - 또한 `"0" + "분/초"`로 더한 뒤 뒤에서부터 2자리를 자르는 방법도 존재.
        - ex) 한 팀의 분/초가 각각 `MM = 7`, `SS = 31`인 경우 `String min = "07"`, `String sec = "031"`
            - `min.substring(min.length() - 2)` = "07", `sec.substring(sec.length() - 2)` = "31"
- 25.8.6. 다시 푼 방법:
    - 현재 득점 시간과 이전 득점 시간의 차이만큼, 리드하던 팀의 누적 시간에 더하며 시간 갱신.
        - 득점 입력이 들어오면, 각 팀의 점수와 현재 시간 갱신.
        - 직전 리드 상황에 따라 각 팀에 누적 시간 더하기.
        - 현재 점수 현황에 따라 리드하는 팀 갱신 및 이전 시간 갱신.
        - 게임 종료 이후 누적 시간 갱신.
    - 알고리즘 자체는 어렵지 않았고, 핵심은 `System.out.printf()`의 활용. 원하는 포맷으로 출력.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int curTime = 0, preTime = 0;
        int one = 0, two = 0;
        int oneCnt = 0, twoCnt = 0;
        int flag = 0;

        for (int i = 0; i < N; i++) {
            String[] tok = br.readLine().split(" ");
            if (Integer.parseInt(tok[0]) == 1) one++;
            else two++;

            String[] time = tok[1].split(":");
            curTime = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);

            if (flag == 1) {
                oneCnt += curTime - preTime;
            } else if (flag == 2) {
                twoCnt += curTime - preTime;
            }

            if (one == two) {
                flag = 0;
            } else if (one > two) {
                flag = 1;
            } else {
                flag = 2;
            }
            preTime = curTime;
        }

        if (flag == 1) {
            oneCnt += 60 * 48 - preTime;
        } else if (flag == 2) {
            twoCnt += 60 * 48 - preTime;
        }

        System.out.printf("%02d:%02d%n", oneCnt / 60, oneCnt % 60);
        System.out.printf("%02d:%02d", twoCnt / 60, twoCnt % 60);
    }
}
```
