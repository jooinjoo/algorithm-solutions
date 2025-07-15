# 미로 탐색

> https://www.acmicpc.net/problem/2178

## 문제 설명

- 문제

N×M크기의 배열로 표현되는 미로가 있다.

미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을
작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

- 입력

첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

- 출력

첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

## 접근 방법

미로 구조를 `int[][] map`에 입력받아, 다음 칸이 1인 경우에만 갱신하며 이동.  
최소의 칸 수를 구하므로 큐를 이용한 BFS 활용.

## 문제 해결 과정

- 최종 해결 방법:
    - 미로의 시작점에서 목표까지 최단 경로를 구해야 하기 때문에, 큐를 이용한 BFS 탐색.
        - 미로를 그래프의 노드와 엣지로 가정, 가중치도 1로 동일. 각 노드는 `map[y][x]` 값에 해당한다.
        - 노드의 값이 1일 때만 이동 가능하며, 다음 노드로 이동할 경우 현재 노드의 +1 만큼 값을 갱신하며 방문 처리까지 수행.
- 25.7.15. 다시 푼 방법:
    - 시작점부터 가중치 1을 전제로 동서남북으로 1일 때만 이동하며 카운팅.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static int N, M;
    static int[][] board;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        M = Integer.parseInt(tok[1]);
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (input[j] == '1') board[i][j] = 1;
            }
        }

        bfs(0, 0);
        System.out.println(board[N - 1][M - 1]);
    }

    static void bfs(int r, int c) {
        Queue<Pos> que = new ArrayDeque<>();
        que.add(new Pos(r, c));

        while (!que.isEmpty()) {
            Pos cur = que.poll();
            if (cur.r == N - 1 && cur.c == M - 1) break;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || board[nr][nc] != 1) continue;
                board[nr][nc] = board[cur.r][cur.c] + 1;
                que.offer(new Pos(nr, nc));
            }
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