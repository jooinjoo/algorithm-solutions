# 주난의 난(難)

> https://www.acmicpc.net/problem/14497

## 문제 설명

- 문제

주난이는 크게 화가 났다. 책상 서랍 안에 몰래 먹으려고 숨겨둔 초코바가 사라졌기 때문이다. 주난이는 미쳐 날뛰기 시작했다. 사실, 진짜로 뛰기 시작했다.

‘쿵... 쿵...’

주난이는 점프의 파동으로 주변의 모든 친구들을 쓰러뜨리고(?) 누군가 훔쳐간 초코바를 찾으려고 한다. 주난이는 N×M크기의 학교 교실 어딘가에서 뛰기 시작했다. 주난이의 파동은 상하좌우 4방향으로 친구들을
쓰러뜨릴(?) 때 까지 계속해서 퍼져나간다. 다르게 표현해서, 한 번의 점프는 한 겹의 친구들을 쓰러뜨린다. 다음의 예를 보자.

1 # 1 0 1 1 1  
1 1 0 1 0 0 1  
0 0 1 * 1 1 1  
1 1 0 1 1 1 1  
0 0 1 1 0 0 1

주난이를 뜻하는 *은 (3, 4)에 있고, 초코바를 가진 학생 #는 (1, 2)에 있다. 0은 장애물이 없는 빈 공간임을 뜻하고, 1은 친구들이 서있음을 의미한다. 다음은 주난이의 점프에 따른 생존(?) 학생들의
변화이다.

1 # 1 0 1 1 1  
1 1 0 0 0 0 1  
0 0 0 * 0 1 1  
1 1 0 0 1 1 1  
0 0 1 1 0 0 1

1 # 0 0 0 0 1  
0 0 0 0 0 0 0  
0 0 0 * 0 0 1  
0 0 0 0 0 1 1  
0 0 0 0 0 0 1

0 X 0 0 0 0 0  
0 0 0 0 0 0 0  
0 0 0 * 0 0 0  
0 0 0 0 0 0 1  
0 0 0 0 0 0 0

위의 예시에서 주난이는 3번의 점프 만에 초코바를 훔쳐간 범인을 찾아낼 수 있다!

주난이를 빨리 멈춰야 교실의 안녕을 도모할 수 있다. 주난이에게 최소 점프 횟수를 알려줘서 교실을 지키자.

## 접근 방법

한 번의 점프를 BFS 탐색의 한 레벨이라고 생각할 수 있다.  
주난의 위치에서 한 레벨 동안 탐색하면, 동서남북으로 1을 만날 때까지 탐색할 수 있고, 1을 만나면 방문 처리한 뒤 다음 노드를 더이상 큐에 넣지 않는다.  
한편 1을 만나기 전 0인 경우 해당 위치부터 또다시 동서남북으로 방문하지 않는 노드를 이동할 수 있으며, 이 경우에도 1을 만나면 종료.  
이 과정에서 범인의 위치를 만나면 종료하며, 모든 큐를 돌았음에도 범인을 만나지 못한 경우 다음 레벨로 넘어가 탐색 반복.  
레벨에 따라 점차 증가하는 탐색 범위를 감안하면, 최대 100 레벨 이하에서 300 * 300 범위를 탐색하므로 시간 복잡도 O(9,000,000).

## 문제 해결 과정

- 최종 해결 방법:
    - `int x1, y1, x2, y2`를 배열의 범위에 맞게 입력.
    - 큐에 주난의 좌표 객체 `Pos(x1, y1)`을 넣고 해당 레벨 `int lev`에서 탐색 가능한 모든 범위 탐색.
        - 매 레벨마다 방문한 노드를 초기화하여, 주난의 위치부터 파동이 퍼지도록 설정.
        - 범인 좌표를 만나면 종료.
        - 현재 노드에서 다음 노드로 움직일 수 있으면 방문 처리 후 삽입 결정.
            - 만약 다음 노드가 `'1'`이라면 해당 노드를 빈 공간인 `'0'`으로 바꾸며 더이상 진행하지 않음.
            - 다음 노드가 `'0'` 또는 `'#'`이라면 큐에 삽입.
                - 범인의 경우에도 큐에 삽입하여, 해당 레벨에서 종료 조건 `if (cur.r == x2 && cur.c == y2)`을 검증하도록 설정.
- 다른 해결 방법:
    - 큐를 동시에 2개 활용하여, 하나는 해당 레벨에서 갈 수 있는 모든 노드를 저장하고 다른 하나는 다음 레벨의 시작 시 삽입되어 있을 노드를 저장한다.
    - 방문 처리 배열을 `int[][] vis`로 선언하여, 해당 노드가 몇 레벨에 탐색했는지 저장.
    - 또한 좌표가 최대 300까지 가능하므로, 노드의 위치를 1000 * r + c 로 저장하는 방식도 사용해 보았다.
    - 현재 `lev`에서 탐색 가능한 노드는 다음 노드의 값이 `'0'`인 경우이다. 따라서 이 경우 `q`에 삽입하며 계속 진행.
        - 한편 다음 노드가 `'1'` 또는 `'#'`과 같은 경우 해당 노드에서 더이상 탐색은 할 수 없다. 탐색을 종료하며 값을 `'0'`올 갱신.
        - 그리고 해당 노드는 다음 레벨에서의 탐색 시작 노드이기도 하다. 따라서 다음 레벨의 노드 시작점을 담은 `tmp` 큐에 삽입.
            - 모든 현재 레벨 탐색이 끝나면 현재 레벨 큐를 다음 레벨 큐로 갱신. `q = tmp`
    - 그리고 BFS 탐색은 범인 노드의 방문 처리가 됐을 때 종료.
- 25.8.12. 다시 푼 방법:
    - 주난의 점프로 이동할 수 있는 칸은 `0`일 때만이다. 따라서 주난 위치부터 시작해, BFS탐색으로 `0`인 가능한 경로는 한번에 전부 이동해야 한다.
    - 한편 이렇게하면 `1`을 만났을 때, 이를 0으로 바꾸고 다시 탐색해야하는 번거로움이 생긴다. 따라서 두 개의 큐를 사용한다.
        - 하나의 큐는 0일 때 이동가능한 경로를 방문처리하는 큐.
        - 또다른 큐는 1을 만나면 임시저장한 뒤, 0으로 이동가능한 탐색이 전부 끝나면 다음 점프 전에 0 이동가능큐로 바꿔주는 큐.
    - 이런식으로 반복하다, 목표 지점이 0으로 바뀌어있으면 도달했다는 뜻이므로 종료.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int n, m, x1, y1, x2, y2, lev;
    static char[][] map;
    static int[][] vis;
    static Queue<Integer> q, tmp;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        map = new char[n][m];
        vis = new int[n][m];
        tok = br.readLine().split(" ");
        x1 = Integer.parseInt(tok[0]) - 1;
        y1 = Integer.parseInt(tok[1]) - 1;
        x2 = Integer.parseInt(tok[2]) - 1;
        y2 = Integer.parseInt(tok[3]) - 1;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        q = new LinkedList<>();
        lev = 0;

        vis[x1][y1] = 1;
        q.offer(1000 * x1 + y1);
        while (map[x2][y2] != '0') {
            lev++;
            tmp = new LinkedList<>();
            while (!q.isEmpty()) {
                int cur = q.poll();
                int cr = cur / 1000;
                int cc = cur % 1000;

                for (int i = 0; i < 4; i++) {
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || vis[nr][nc] != 0) continue;
                    vis[nr][nc] = lev;
                    if (map[nr][nc] != '0') {
                        map[nr][nc] = '0';
                        tmp.add(nr * 1000 + nc);
                    } else {
                        q.offer(nr * 1000 + nc);
                    }
                }
            }
            q = tmp;
        }
        System.out.println(vis[x2][y2]);
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

    static int N, M, x1, y1, x2, y2, ans = 0;
    static char[][] board;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        N = Integer.parseInt(tok[0]);
        M = Integer.parseInt(tok[1]);
        tok = br.readLine().split(" ");
        x1 = Integer.parseInt(tok[0]) - 1;
        y1 = Integer.parseInt(tok[1]) - 1;
        x2 = Integer.parseInt(tok[2]) - 1;
        y2 = Integer.parseInt(tok[3]) - 1;
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == '#') board[i][j] = '1';
            }
        }

        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x1, y1));
        vis = new boolean[N][M];
        vis[x1][y1] = true;
        Queue<Pos> tmp;

        while (true) {
            if (board[x2][y2] == '0') break;
            ans++;

            tmp = new LinkedList<>();
            while (!que.isEmpty()) {
                Pos cur = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nc < 0 || nr == N || nc == M || vis[nr][nc]) continue;
                    vis[nr][nc] = true;
                    if (board[nr][nc] == '0') {
                        que.offer(new Pos(nr, nc));
                    } else if (board[nr][nc] == '1') {
                        board[nr][nc] = '0';
                        tmp.offer(new Pos(nr, nc));
                    }
                }
            }
            que = tmp;
        }

        System.out.println(ans);
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