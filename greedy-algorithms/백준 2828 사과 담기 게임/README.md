# 사과 담기 게임

> https://www.acmicpc.net/problem/2828

## 문제 설명

- 문제

상근이는 오락실에서 바구니를 옮기는 오래된 게임을 한다. 스크린은 N칸으로 나누어져 있다. 스크린의 아래쪽에는 M칸을 차지하는 바구니가 있다. (M<N) 플레이어는 게임을 하는 중에 바구니를 왼쪽이나 오른쪽으로
이동할 수 있다. 하지만, 바구니는 스크린의 경계를 넘어가면 안 된다. 가장 처음에 바구니는 왼쪽 M칸을 차지하고 있다.

스크린의 위에서 사과 여러 개가 떨어진다. 각 사과는 N칸중 한 칸의 상단에서 떨어지기 시작하며, 스크린의 바닥에 닿을때까지 직선으로 떨어진다. 한 사과가 바닥에 닿는 즉시, 다른 사과가 떨어지기 시작한다.

바구니가 사과가 떨어지는 칸을 차지하고 있다면, 바구니는 그 사과가 바닥에 닿을 때, 사과를 담을 수 있다. 상근이는 사과를 모두 담으려고 한다. 이때, 바구니의 이동 거리의 최솟값을 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 N과 M이 주어진다. (1 ≤ M < N ≤ 10) 둘째 줄에 떨어지는 사과의 개수 J가 주어진다. (1 ≤ J ≤ 20) 다음 J개 줄에는 사과가 떨어지는 위치가 순서대로 주어진다.

- 출력

모든 사과를 담기 위해서 바구니가 이동해야 하는 거리의 최솟값을 출력한다.

## 접근 방법

사과 바구니의 좌우 범위 `lt`, `rt`를 사과가 떨어지는 위치로 이동.  
이 과정에서 사과까지 최소 이동거리 `tmp`를 구해 더하고, `lt`, `rt`도 조정.  
시간복잡도 O(N)의 간단한 구현 문제.

## 문제 해결 과정

- 최종 해결 방법:
    - 가장 처음의 바구니는 왼쪽에서 M칸을 차지하므로, `lt = 1`, `rt = M` 시작.
    - 현재 사과가 떨어지는 위치 `cur`이 `lt`와 `rt`의 범위에 있다면 넘기기.
    - 그렇지 않다면, `cur`까지의 거리를 구해, 누적 거리에 합하며 `lt`와 `rt`의 범위도 조정한다.
        - 범위 조정은 `cur`이 바구니의 왼쪽에 위치하는지, 오른쪽에 위치하는지만 판단하면 된다.
- 25.7.30. 다시 푼 방법:
    - 초기의 바구니 상태 좌우 끝을 `lt = 1`, `rt = M`으로 놓고 시작.
    - 다음 사과가 떨어지는 위치를 `lt`, `rt`와 비교하며 해당 값 사이에 떨어지면 이동하는 값 `tmp = 0`이 된다.
        - 그렇지 않고 더 작으면 `lt`와의 차이, 더 크면 `rt`와의 차이만큼 `cnt`에 더해준다.
        - 동시에 `lt`, `rt`의 값을 `tmp`만큼 조정.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N, M, J, lt, rt, cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        M = Integer.parseInt(tok[1]);
        J = Integer.parseInt(br.readLine());
        lt = 1;
        rt = M;
        for (int i = 0; i < J; i++) {
            int cur = Integer.parseInt(br.readLine());
            int tmp = 0;
            if (cur < lt) {
                tmp = lt - cur;
                lt -= tmp;
                rt -= tmp;
            } else if (cur > rt) {
                tmp = cur - rt;
                lt += tmp;
                rt += tmp;
            }
            cnt += tmp;
        }

        System.out.println(cnt);
    }
}
```