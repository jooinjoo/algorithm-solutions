# 컴백홈

> https://www.acmicpc.net/problem/1189

## 문제 설명

- 문제

한수는 캠프를 마치고 집에 돌아가려 한다. 한수는 현재 왼쪽 아래점에 있고 집은 오른쪽 위에 있다. 그리고 한수는 집에 돌아가는 방법이 다양하다. 단, 한수는 똑똑하여 한번 지나친 곳을 다시 방문하지는 않는다.

      cdef  ...f  ..ef  ..gh  cdeh  cdej  ...f 
      bT..  .T.e  .Td.  .Tfe  bTfg  bTfi  .Tde 
      a...  abcd  abc.  abcd  a...  a.gh  abc. 

거리 :  6 6 6 8 8 10 6
위 예제는 한수가 집에 돌아갈 수 있는 모든 경우를 나타낸 것이다. T로 표시된 부분은 가지 못하는 부분이다. 문제는 R x C 맵에 못가는 부분이 주어지고 거리 K가 주어지면 한수가 집까지도 도착하는 경우 중
거리가 K인 가짓수를 구하는 것이다.

- 입력

첫 줄에 정수 R(1 ≤ R ≤ 5), C(1 ≤ C ≤ 5), K(1 ≤ K ≤ R×C)가 공백으로 구분되어 주어진다. 두 번째부터 R+1번째 줄까지는 R×C 맵의 정보를 나타내는 '.'과 'T'로 구성된 길이가
C인 문자열이 주어진다.

- 출력

첫 줄에 거리가 K인 가짓수를 출력한다.

## 접근 방법

문제는 정해진 시작점에서 종료지점까지 거리가 K만큼 걸려 도착할 수 있는 가짓수를 필요로 한다.   
따라서 방문하지 않은 지점만 방문하면서 K만큼 이동하는 모든 경우의 수에 대해, 해당 종료지점이 목표이면 카운팅.  
DFS 탐색을 통해 가능한 지점만 골라가는 백트래킹 기법으로 충분히 해결할 수 있다.

## 문제 해결 과정

- 최종 해결 방법:
    - 우선 K가 최단 경로(R + C - 1) 보다 작은 경우는 0을 출력하며 조기 종료.
    - 그렇지 않다면, 시작점(R - 1, 0)부터 방문 처리하며 해당 지점까지 걸린 거리(`cnt`)가 `K`가 될 때까지 `dfs()` 탐색.
        - 만약 거리가 K만큼 채워졌을 때 목표지점이면 카운팅 `ans++`, 그렇지 않으면 바로 종료.
        - 동서남북으로 다음 노드가 방문할 수 있고, 방문한 적 없을 때만 다음 노드를 방문 처리하며 방문한다.
        - 그리고 해당 방문이 끝나면 방문 처리 원상 복구 `vis[nr][nc] = false`.
- 다른 해결 방법:
    - 전역 변수 `ans`를 사용하지 않고, `dfs()` 메서드가 직접 조건을 만족하는 경우의 수를 출력하는 방식 사용.
    - 방문 처리를 `int[][] vis`로 바꿔 해당 좌표까지 거리가 얼마나 걸렸는지 알 수 있다.
        - `dfs()`를 재귀하며 좌표가 목표 지점에 다다랐을 때, 걸린 거리를 체크.
            - 거리가 `K`와 같다면 1, 그렇지 않으면 0 리턴.
        - 재귀가 끝나면 현재 좌표에서 가능한 경우의 수를 전부 더해 리턴.
- 25.8.17. 다시 푼 방법:
    - DFS 탐색을 통해 시작 지점부터 종료 지점까지 `K`만큼 거리가 소요된 거리 수를 카운팅.
        - `dfs()`함수의 재귀를 통해, 동서남북으로 방문하지 않은 지점만 방문하되, 목표 지점에 도착했을 때 종료하도록 기저사례 설정.
        - 함수 재귀 이후 다른 경로로는 다시 방문할 수 있도록 원상복구.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int R, C, K;
    static char[][] map;
    static int[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        R = Integer.parseInt(tok[0]);
        C = Integer.parseInt(tok[1]);
        K = Integer.parseInt(tok[2]);
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        vis = new int[R][C];

        if (K < R + C - 1) {
            System.out.println(0);
            return;
        }

        vis[R - 1][0] = 1;
        System.out.println(dfs(R - 1, 0));
    }

    static int dfs(int r, int c) {
        if (r == 0 && c == C - 1) {
            if (vis[r][c] == K) return 1;
            return 0;
        }

        int ret = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (map[nr][nc] == 'T' || vis[nr][nc] != 0) continue;
            vis[nr][nc] = vis[r][c] + 1;
            ret += dfs(nr, nc);
            vis[nr][nc] = 0;
        }
        return ret;
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int R, C, K, ans = 0;
    static char[][] board;
    static int[][] vis;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        R = Integer.parseInt(tok[0]);
        C = Integer.parseInt(tok[1]);
        K = Integer.parseInt(tok[2]);
        board = new char[R][C];
        vis = new int[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        vis[R - 1][0] = 1;
        dfs(R - 1, 0, 1);

        System.out.println(ans);
    }

    static void dfs(int r, int c, int cnt) {
        if (r == 0 && c == C - 1) {
            if (cnt == K) ans++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || board[nr][nc] != '.' || vis[nr][nc] != 0) continue;
            vis[nr][nc] = vis[r][c] + 1;
            dfs(nr, nc, cnt + 1);
            vis[nr][nc] = 0;
        }
    }
}
```