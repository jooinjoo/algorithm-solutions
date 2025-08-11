# 뮤탈리스크

> https://www.acmicpc.net/problem/12869

## 문제 설명

- 문제

수빈이는 강호와 함께 스타크래프트 게임을 하고 있다. 수빈이는 뮤탈리스크 1개가 남아있고, 강호는 SCV N개가 남아있다.

각각의 SCV는 남아있는 체력이 주어져있으며, 뮤탈리스크를 공격할 수는 없다. 즉, 이 게임은 수빈이가 이겼다는 것이다.

뮤탈리스크가 공격을 할 때, 한 번에 세 개의 SCV를 공격할 수 있다.

첫 번째로 공격받는 SCV는 체력 9를 잃는다.
두 번째로 공격받는 SCV는 체력 3을 잃는다.
세 번째로 공격받는 SCV는 체력 1을 잃는다.
SCV의 체력이 0 또는 그 이하가 되어버리면, SCV는 그 즉시 파괴된다. 한 번의 공격에서 같은 SCV를 여러 번 공격할 수는 없다.

남아있는 SCV의 체력이 주어졌을 때, 모든 SCV를 파괴하기 위해 공격해야 하는 횟수의 최솟값을 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 SCV의 수 N (1 ≤ N ≤ 3)이 주어진다. 둘째 줄에는 SCV N개의 체력이 주어진다. 체력은 60보다 작거나 같은 자연수이다.

- 출력

첫째 줄에 모든 SCV를 파괴하기 위한 공격 횟수의 최솟값을 출력한다.

## 접근 방법

세 SCV 각각의 체력을 3차원 배열의 인덱스 값이라고 간주하면, 세 체력이 모두 0이 될 때는 (0, 0, 0)이라고 볼 수 있다.  
따라서 각각의 첫 체력이 최대 인덱스 값이며, BFS 탐색을 통해 뺄 수 있는 모든 경우의 수를 빼며 방문 처리 한다.  
이 과정에서 (0, 0, 0)에 도착한다면, 방문 처리하며 가장 빨리 도착한 경우이므로 지금까지의 방문 누적값을 출력.  
최대 체력은 60이므로 (60^3)의 시간 복잡도, 약 22만의 경우의 수.

## 문제 해결 과정

- 첫 번째 시도:
    - N에 따라 나올 수 있는 모든 순열 조합을 저장한 뒤, BFS 탐색하며 각각의 순열 조합을 현재 체력값에 빼주며 루프.
        - 아이디어 자체는 맞았지만, 특정 자료구조에 방문 처리를 하지 않다보니 메모리 초과로 실패.
- 최종 해결 방법:
    - 최대 3개의 SCV이므로, 뺄 수 있는 순열의 모든 조합을 미리 `int[][] sub`에 저장.
        - SCV가 1 또는 2인 경우에 9 또는 6부터 빼지 않는 경우가 있어 잘못됐지만, 시간 복잡도로 보나 정답 도출로 보나 상관 없음.
    - 최대 체력은 60이므로, `vis = new int[61][61][61]`에 최초 체력부터 방문 처리 시작.
        - 예를 들어, (12, 10, 4)의 경우 `vis[12][10][4] = 1`.
        - 이후 `sub[5] = {9, 3, 1}`을 빼면  `vis[3][7][3] = 2` 이런 식으로 갱신된다.
            - 한편 `sub`의 원소를 빼던 도중 배열의 인덱스가 0 미만으로 떨어지면 안되기 때문에 `Math.max(0, cur[0] - sub[i][0])`으로 조정.
    - 이미 값이 있는 경우는 방문 처리한 케이스기 때문에 건너 뛴다.
    - 이 과정에서 모든 인덱스의 값이 0이 되는 순간 종료.
    - 3차원 배열에 대한 방문 처리는 생각치 못해 시간이 오래 걸렸다. 반드시 2차원 배열만 방문 처리한다는 생각을 버리자.
- 25.8.11. 다시 푼 방법:
    - 최대 N개의 SCV의 체력을 빼는 순열의 조합을 `perm()` 메서드를 통해 `order`에 저장한다.
    - 모든 SCV를 파괴하는 공격 횟수의 최솟값을 구하는 문제이므로, BFS를 통해 초기 체력 상태에서 시작해 각 상태에서 뻗어나갈 수 있는 체력 조합을 체크해 나간다.
        - `order`를 하나씩 적용하며 각 SCV 체력 상태를 `dp`에 저장하며, `dp`의 각 인덱스가 전부 0이 되는 시점에 종료.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int N;
    static int[] vals, d = {9, 3, 1};
    static ArrayList<int[]> order;
    static int[][][] dp;
    static int[] tmp;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        vals = new int[3];
        String[] tok = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            vals[i] = Integer.parseInt(tok[i]);
        }
        order = new ArrayList<>();
        dp = new int[61][61][61];
        dp[vals[0]][vals[1]][vals[2]] = 1;

        // 순열 조합 만들고
        tmp = new int[3];
        vis = new boolean[3];
        perm(0);

        // 만든 순열을 토대로 체력 줄여나가기
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{vals[0], vals[1], vals[2]});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (cur[0] == 0 && cur[1] == 0 && cur[2] == 0) break;

            for (int[] sub : order) {
                int na = Math.max(cur[0] - d[sub[0]], 0);
                int nb = Math.max(cur[1] - d[sub[1]], 0);
                int nc = Math.max(cur[2] - d[sub[2]], 0);
                if (dp[na][nb][nc] != 0) continue;
                dp[na][nb][nc] = dp[cur[0]][cur[1]][cur[2]] + 1;
                que.offer(new int[]{na, nb, nc});
            }
        }

        System.out.println(dp[0][0][0] - 1);
    }

    static void perm(int idx) {
        if (idx == N) {
            order.add(tmp.clone());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            tmp[idx] = i;
            perm(idx + 1);
            vis[i] = false;
        }
    }
}
```