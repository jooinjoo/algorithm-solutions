# 불!

> https://www.acmicpc.net/problem/4179

## 문제 설명

- 문제

지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!

미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.

지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.

불은 각 지점에서 네 방향으로 확산된다.

지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.

지훈이와 불은 벽이 있는 공간은 통과하지 못한다.

- 입력

입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.

다음 입력으로 R줄동안 각각의 미로 행이 주어진다.

각각의 문자들은 다음을 뜻한다.

#: 벽
.: 지나갈 수 있는 공간
J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
F: 불이 난 공간
J는 입력에서 하나만 주어진다.

- 출력

지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.

지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.

## 접근 방법

문제는 매 분마다 불의 움직임과 지훈이의 움직임이 동시에 한칸씩 일어난다.  
따라서 두 개의 큐를 사용해, 각각 BFS 탐색으로 매 분마다 현재 레벨에서 움직일 수 있는 모든 움직임을 수행한 뒤, 새로운 움직임을 큐에 삽입하였다.   
이 과정에서 불과 지훈이 같은 시간에 둘다 접근 가능한 경우는 갈 수 없어야 하므로, 불 먼저 Flood Fill 진행.  
지훈이 미로의 가장자리에 접근하는 순간 걸린 시간(BFS 레벨)을 출력하며 종료한다.
시간 복잡도는 최대 O(R * C), R과 C는 최대 1000이므로 여유 있게 통과.

## 문제 해결 과정

- 최종 해결 방법:
    - 지훈의 BFS 탐색 큐 `Queue<Pos> jq`, 불의 BFS 탐색 큐 `Queue<Pos> fq`에 각각의 탐색 좌표를 삽입.
    - 지훈의 이동 가능 범위를 판단해야 하기 때문에, `jq` 큐에 값이 있는 동안 반복.
        - 불 먼저 이동한다. 루프 시작 시점의 불 큐 크기 `fSize`만큼 큐에서 뽑으면, 현재 레벨에 해당하는 불 좌표 이동.
            - `map[nr][nc] = 'F'`을 통해 지훈이 갈 수 없도록 방문 처리.
        - 다음으로 지훈 이동. 현재 레벨에서 이동할 수 있는 지훈의 좌표 수 `jSize`만큼 다음 좌표로 이동.
            - 만약 다음 좌표에서 미로의 가장자리에 도달할 수 있으면 루프 종료.
            - 그렇지 않으면 지훈의 해당 좌표까지 걸린 시간을 `vis[nr][nc]`에 갱신.
    - 더이상 지훈의 이동 가능 좌표가 없으면 탈출 실패.
- 다른 해결 방법:
    - 한 레벨에서 불과 지훈이 동시에 이동하지 않고, 불 먼저 모든 범위까지 걸리는 시간을 저장한 뒤 지훈 이동.
        - 각각 큐를 쓰는 대신, 각각 방문 처리를 하도록 불 `visF`, 지훈 `visJ` 배열을 사용.
        - 불 먼저 시작지점부터 BFS 탐색을 통해 최단 거리로 불이 퍼져나가도록 `visF`를 저장.
        - 지훈도 시작점부터 BFS로 갈 수 있는 모든 범위를 탐색한다.
            - 이 때 다음 노드에 도착할 때 걸린 시간이 불의 방문 처리 시간보다 작도록 방문하는 것이 핵심.
- 25.8.11. 다시 푼 방법:
    - 불과 지훈은 동시에 이동한다. 하지만 불이 먼저, 그리고 이후에 지훈이 이동해야 한다.
        - 각각 불의 큐 `fQue`와 지훈의 큐 `jQue`를 이용하여, 각각 이동할 수 있는 경우를 BFS 탐색.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int r, c, jr = 0, jc = 0;
    static char[][] map;
    static int[][] visF, visJ;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        r = Integer.parseInt(tok[0]);
        c = Integer.parseInt(tok[1]);
        map = new char[r][c];
        visF = new int[r][c];
        for (int[] i : visF) {
            Arrays.fill(i, Integer.MAX_VALUE);
        }
        visJ = new int[r][c];
        Queue<Pos> q = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'J') {
                    map[i][j] = '.';
                    jr = i;
                    jc = j;
                } else if (map[i][j] == 'F') {
                    q.offer(new Pos(i, j));
                    visF[i][j] = 1;
                }
            }
        }

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];
                if (nr < 0 || nc < 0 || nr >= r || nc >= c || map[nr][nc] != '.') continue;
                if (visF[nr][nc] != Integer.MAX_VALUE) continue;
                visF[nr][nc] = visF[cur.row][cur.col] + 1;
                q.offer(new Pos(nr, nc));
            }
        }

        q.offer(new Pos(jr, jc));
        visJ[jr][jc] = 1;
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (cur.row == 0 || cur.col == 0 || cur.row == r - 1 || cur.col == c - 1) {
                System.out.println(visJ[cur.row][cur.col]);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];
                if (nr < 0 || nc < 0 || nr >= r || nc >= c || map[nr][nc] != '.') continue;
                if (visJ[nr][nc] != 0 || visJ[cur.row][cur.col] + 1 >= visF[nr][nc]) continue;
                visJ[nr][nc] = visJ[cur.row][cur.col] + 1;
                q.offer(new Pos(nr, nc));
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int R, C, ans = 0;
    static char[][] board;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        R = Integer.parseInt(tok[0]);
        C = Integer.parseInt(tok[1]);
        board = new char[R][C];
        vis = new boolean[R][C];
        Queue<Pos> jQue = new LinkedList<>();
        Queue<Pos> fQue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'J') {
                    jQue.add(new Pos(i, j));
                    vis[i][j] = true;
                    board[i][j] = '.';
                } else if (board[i][j] == 'F') fQue.add(new Pos(i, j));
            }
        }

        boolean flag = false;
        while (!jQue.isEmpty()) {
            int fSize = fQue.size();
            for (int i = 0; i < fSize; i++) {
                Pos fire = fQue.poll();
                for (int j = 0; j < 4; j++) {
                    int nr = fire.r + dr[j];
                    int nc = fire.c + dc[j];
                    if (nr < 0 || nc < 0 || nr == R || nc == C || board[nr][nc] != '.') continue;
                    fQue.offer(new Pos(nr, nc));
                    board[nr][nc] = 'F';
                }
            }

            int jSize = jQue.size();
            for (int i = 0; i < jSize; i++) {
                Pos cur = jQue.poll();
                for (int j = 0; j < 4; j++) {
                    int nr = cur.r + dr[j];
                    int nc = cur.c + dc[j];
                    if (nr < 0 || nc < 0 || nr == R || nc == C) {
                        flag = true;
                        break;
                    }
                    if (vis[nr][nc] || board[nr][nc] != '.') continue;
                    vis[nr][nc] = true;
                    jQue.offer(new Pos(nr, nc));
                }
            }

            ans++;
            if (flag) break;
        }

        if (flag) System.out.println(ans);
        else System.out.println("IMPOSSIBLE");
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