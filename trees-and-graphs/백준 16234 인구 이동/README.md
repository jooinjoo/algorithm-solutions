# 인구 이동

> https://www.acmicpc.net/problem/16234

## 문제 설명

- 문제

N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다. 인접한 나라 사이에는 국경선이 존재한다. 모든 나라는
1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.

오늘부터 인구 이동이 시작되는 날이다.

인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.

국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
연합을 해체하고, 모든 국경선을 닫는다.
각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)

둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)

인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.

- 출력

인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.

## 접근 방법

해당 날짜에 인구 이동이 한 번이라도 발생할 수 있다면 다음 날로 진행할 수 있다.  
따라서 날짜가 초기화 될 때마다, 방문 기록을 초기화한 뒤 모든 나라를 탐색하여 인구 이동이 발생했는지 검사.  
이 과정에서 BFS 탐색을 이용해 인구 이동이 발생하는 나라끼리는, 하나의 컴포넌트로서 연합 내의 인구를 재조정.  
N이 최대 50이고 인구 이동 발생 일수가 최대 2000인데, BFS 과정에서 방문한 곳은 재방문하지 않으므로 시간복잡도는 사실상 O(2000 * N^2)라고 볼 수 있다.  
따라서 최대 500만 경우의 수로 통과.

## 문제 해결 과정

- 최종 해결 방법:
    - `while (true)` 문을 통해 현재 날짜에 인구 이동이 발생하면 계속 반복하도록 설정.
        - 인구 이동이 한 번이라도 일어났는지 확인하는 지표 `boolean flag` 사용.
        - `flag`는 `bfs()` 함수 내에서 다음 좌표에 대해 1) 방문한 적 없고, 2) 인구 차이가 l이상 r이하를 만족할 때만 `true`로 갱신.
    - 매 루프마다 방문 처리 `vis = new boolean[n][n]` 초기화한 뒤 방문한 적 없는 나라에 대해서만 `bfs(i, j)` 시작.
        - 시작 지점 `(r, c)`를 큐에 넣고 다음 노드로 이동할 수 있으면 인구 이동.
        - 인구 이동이 일어날 때마다 해당 나라(좌표)를 인구 이동한 나라의 리스트 `ArrayList<Pos> open`에 추가.
            - 가능한 모든 BFS 탐색이 끝나면 리스트의 좌표 인구를 `map[pos.r][pos.c] = tmp / open.size()` 재설정.
    - 만약 모든 좌표를 탐색한 후에도 `flag = false`라면 종료.
- 다른 해결 방법:
    - DFS 탐색으로도 풀어보았다.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int n, l, r, cnt, tmp;
    static boolean flag;
    static int[][] map;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static ArrayList<Pos> open;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        l = Integer.parseInt(tok[1]);
        r = Integer.parseInt(tok[2]);
        cnt = 0;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
            }
        }

        while (true) {
            flag = false;
            vis = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!vis[i][j]) {
                        open = new ArrayList<>();
                        vis[i][j] = true;
                        open.add(new Pos(i, j));
                        tmp = map[i][j];
                        dfs(i, j);
                        if (open.size() > 1) {
                            for (Pos pos : open) {
                                map[pos.r][pos.c] = tmp / open.size();
                                flag = true;
                            }
                        }
                    }
                }
            }

            if (!flag) break;
            cnt++;
        }
        System.out.println(cnt);
    }

    static void dfs(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n || vis[nr][nc]) continue;
            int diff = Math.abs(map[nr][nc] - map[row][col]);
            if (diff < l || r < diff) continue;
            vis[nr][nc] = true;
            open.add(new Pos(nr, nc));
            tmp += map[nr][nc];
            dfs(nr, nc);
        }
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