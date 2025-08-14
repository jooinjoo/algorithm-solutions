# 사다리 조작

> https://www.acmicpc.net/problem/15684

## 문제 설명

- 문제

사다리 게임은 N개의 세로선과 M개의 가로선으로 이루어져 있다. 인접한 세로선 사이에는 가로선을 놓을 수 있는데, 각각의 세로선마다 가로선을 놓을 수 있는 위치의 개수는 H이고, 모든 세로선이 같은 위치를 갖는다.
아래 그림은 N = 5, H = 6 인 경우의 그림이고, 가로선은 없다.

초록선은 세로선을 나타내고, 초록선과 점선이 교차하는 점은 가로선을 놓을 수 있는 점이다. 가로선은 인접한 두 세로선을 연결해야 한다. 단, 두 가로선이 연속하거나 서로 접하면 안 된다. 또, 가로선은 점선 위에
있어야 한다.

위의 그림에는 가로선이 총 5개 있다. 가로선은 위의 그림과 같이 인접한 두 세로선을 연결해야 하고, 가로선을 놓을 수 있는 위치를 연결해야 한다.

사다리 게임은 각각의 세로선마다 게임을 진행하고, 세로선의 가장 위에서부터 아래 방향으로 내려가야 한다. 이때, 가로선을 만나면 가로선을 이용해 옆 세로선으로 이동한 다음, 이동한 세로선에서 아래 방향으로 이동해야
한다.

위의 그림에서 1번은 3번으로, 2번은 2번으로, 3번은 5번으로, 4번은 1번으로, 5번은 4번으로 도착하게 된다. 아래 두 그림은 1번과 2번이 어떻게 이동했는지 나타내는 그림이다.

1번 세로선 2번 세로선
사다리에 가로선을 추가해서, 사다리 게임의 결과를 조작하려고 한다. 이때, i번 세로선의 결과가 i번이 나와야 한다. 그렇게 하기 위해서 추가해야 하는 가로선 개수의 최솟값을 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 세로선의 개수 N, 가로선의 개수 M, 세로선마다 가로선을 놓을 수 있는 위치의 개수 H가 주어진다. (2 ≤ N ≤ 10, 1 ≤ H ≤ 30, 0 ≤ M ≤ (N-1)×H)

둘째 줄부터 M개의 줄에는 가로선의 정보가 한 줄에 하나씩 주어진다.

가로선의 정보는 두 정수 a과 b로 나타낸다. (1 ≤ a ≤ H, 1 ≤ b ≤ N-1) b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결했다는 의미이다.

가장 위에 있는 점선의 번호는 1번이고, 아래로 내려갈 때마다 1이 증가한다. 세로선은 가장 왼쪽에 있는 것의 번호가 1번이고, 오른쪽으로 갈 때마다 1이 증가한다.

입력으로 주어지는 가로선이 서로 연속하는 경우는 없다.

- 출력

i번 세로선의 결과가 i번이 나오도록 사다리 게임을 조작하려면, 추가해야 하는 가로선 개수의 최솟값을 출력한다. 만약, 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.

## 접근 방법

추가해야 하는 가로선은 0부터 3까지 가능하고, 가능한 최소로 가로선을 추가할 수 있는 문제.  
한편 최대 시간 복잡도는 300(모든 사다리 탐색 N * H) * 300C3(사다리 조합 뽑기) = 약 13억 정도이므로 완전 탐색은 불가능.  
그러므로 가로선을 추가를 0부터 시작하여, 가능한 가로선 추가 조합이 나오는 순간 탐색을 종료하고 출력하도록 접근하였다.

## 문제 해결 과정

- 첫 번째 시도:
    - 가로선 조합을 뽑는 과정에서 이전에 탐색했던 중복된 경우의 수를 재탐색하여 시간 초과.
- 최종 해결 방법:
    - (r, c) 좌표에 가로선이 있는 지 판단하는 `int[][] map` 배열에 우측으로 가는 가로선이 존재하면 `1`, 좌측이면 `2`를 입력.
    - 가로선 추가 조합을 뽑는 함수 `comb()`를 0부터 3까지 반복하며 가능한 조합이 나오면 즉시 종료하고 출력.
        - `cnt == size`로 조합 수가 완성된 순간 `check()` 함수를 통해 모든 i번 세로선의 결과가 i번이 나오는지 검증.
        - 아직 조합이 완성되지 않았으면, 해당 `size` 기준으로 좌우로 연결되지 않은 경우에 대해 가로선 넣은 뒤 재귀. 이후 원상 복구.
- 다른 해결 방법:
    - 0부터 3까지 순서대로 탐색하는 방법 대신, 최댓값 `ans` 기준으로 더 작은 가로선 추가 조합에 대해서만 백트래킹 탐색.
    - 또한 `map`의 원소도 좌우 이동을 각각 나누기 보단 우로 뻗어나가는 원소만 설정하여 탐색하였다.
- 25.8.14. 다시 푼 방법:
    - 특정 가로선을 표기할 때, 왼쪽에서 오른쪽으로 이동하는, 즉 해당 행과 좌측 열의 값만 `true`로 표기하였다.
        - 예) (1, 1) 가로선 -> `board[1][1] = true` -> (1, 1)에서는 우측이동, (1, 2)에서는 좌측이동.
    - 이후 전체를 루프하며 현재 가로선이 들어갈 삽입될 수 있는 모든 위치를 `ArrayList<Pos> vals`에 삽입.
        - 해당 원소들에서 최대 0~3까지 조합할 수 있는 메서드 `comb()`를 통해 최소로 추가하여 문제의 요구를 만족하는 결과를 도출.
            - 해당 재귀 함수에서 조합에 선택한 수 `cnt`가 3을 넘으면 종료. 그렇지 않으면 일단 현재 상태에서 i -> i로 갈 수 있는지 `check()`로 확인.
            - 만족하지 않으면 다음 조합을 선택한 후 원상복구 반복.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n, m, h, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        h = Integer.parseInt(tok[2]);
        ans = Integer.MAX_VALUE;
        map = new int[h + 1][n + 1];
        for (int i = 0; i < m; i++) {
            tok = br.readLine().split(" ");
            int a = Integer.parseInt(tok[0]);
            int b = Integer.parseInt(tok[1]);
            map[a][b] = 1;
        }

        comb(1, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void comb(int idx, int cnt) {
        if (cnt > 3 || cnt >= ans) return;
        if (check()) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = idx; i <= h; i++) {
            for (int j = 1; j <= n - 1; j++) {
                if (map[i][j] == 1 || map[i][j - 1] == 1 || map[i][j + 1] == 1) continue;
                map[i][j] = 1;
                comb(i, cnt + 1);
                map[i][j] = 0;
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) {
            int r = 1, c = i;
            for (int j = 1; j <= h; j++) {
                if (map[r][c] == 1) c++;
                else if (map[r][c - 1] == 1) c--;
                r++;
            }
            if (i != c) return false;
        }
        return true;
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int N, M, H, ans = 4;
    static boolean[][] board;
    static ArrayList<Pos> vals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        M = Integer.parseInt(tok[1]);
        H = Integer.parseInt(tok[2]);
        board = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            tok = br.readLine().split(" ");
            int r = Integer.parseInt(tok[0]);
            int c = Integer.parseInt(tok[1]);
            board[r][c] = true;
        }

        vals = new ArrayList<>();
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (!board[i][j]) vals.add(new Pos(i, j));
            }
        }

        comb(0, 0);

        System.out.println(ans == 4 ? -1 : ans);
    }

    static void comb(int idx, int cnt) {
        if (cnt > 3 || cnt >= ans) return;
        if (check()) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = idx; i < vals.size(); i++) {
            Pos cur = vals.get(i);
            if (board[cur.r][cur.c] || board[cur.r][cur.c - 1] || board[cur.r][cur.c + 1]) continue;
            board[cur.r][cur.c] = true;
            comb(i + 1, cnt + 1);
            board[cur.r][cur.c] = false;
        }
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int r = 1;
            int c = i;

            while (r <= H) {
                if (board[r][c]) {
                    c++;
                } else if (board[r][c - 1]) {
                    c--;
                }
                r++;
            }
            if (c != i) return false;
        }
        return true;
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
```