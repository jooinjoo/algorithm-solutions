# 유기농 배추

> https://www.acmicpc.net/problem/1012

## 문제 설명

- 문제

차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로
결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시
해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.

한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가
필요한지 알 수 있다. 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.

- 입력

입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 그리고
배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.

- 출력

각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.

## 접근 방법

각 테스트 케이스 별로 땅을 `int[][] map`에 옮겨, 배추의 위치 `(r, c)`에 `1`로 입력.  
모든 `map`을 차례로 탐색하며, 배추를 만나면 연결된 모든 배추를 `0`으로 방문 처리.  
BFS를 통해 연결된 배추를 모두 탐색한 뒤, 해충 카운팅.

## 문제 해결 과정

- 최종 해결 방법:
    - `map`의 전체를 한번씩 탐색하며, 배추를 만나면 큐에 현재 위치 `Pos`를 넣고 BFS 탐색으로 인접한 모든 배추 탐색.
        - 배추가 심어져 있거나, 그렇지 않은 땅을 하나의 노드로 가정하고, 배추가 있는 `1`일 때만 동서남북 방향으로 이동 가능.
        - 최소한의 해충을 써야하기 때문에, 한 마리의 해충으로 가능한 모든 배추를 탐색하며 `0`으로 갱신하여 방문처리.
- 다른 해결 방법:
    - 결국 해충의 수는 그래프에서 Connected component가 몇 개냐 묻는 문제. 따라서 DFS 탐색으로도 풀어보았다.
        - 크게 달라지는 부분은 없고, 직접 다음 노드의 `(r, c)`를 `dfs()` 메서드에 지정할 수 있어, `Pos` 객체를 쓰지 않아도 됨.
        - 한편 유의할 점으로 다음 노드의 `(nr, nc)`를 검증하는 과정에서 `map`의 범위를 먼저 검증한 뒤, `map[nr][nc]`이 배추인지 검증.
            - 이 순서를 지키지 않으면 `ArrayIndexOutOfBoundsException` 예외.
- 25.1.25. 다시 푼 밥업:
  - 전체 배추밭을 한번씩 탐색하여 배추를 심은 곳을 만날 때마다 BFS 탐색.
  - 동서남북 방향으로 인접한 배추를 모두 체크한 뒤, 하나의 컴포넌트를 끝내고 카운팅.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int t, n, m, k;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] strs = br.readLine().split(" ");
            m = Integer.parseInt(strs[0]);
            n = Integer.parseInt(strs[1]);
            k = Integer.parseInt(strs[2]);
            map = new int[n][m];
            for (int i = 0; i < k; i++) {
                strs = br.readLine().split(" ");
                map[Integer.parseInt(strs[1])][Integer.parseInt(strs[0])] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        map[i][j] = 0;
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0) continue;
            map[nr][nc] = 0;
            dfs(nr, nc);
        }
    }
}
```

### 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int N, M, K, ans;
    static boolean[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] tok = br.readLine().split(" ");
            M = Integer.parseInt(tok[0]);
            N = Integer.parseInt(tok[1]);
            K = Integer.parseInt(tok[2]);
            ans = 0;
            map = new boolean[N][M];
            for (int i = 0; i < K; i++) {
                tok = br.readLine().split(" ");
                int c = Integer.parseInt(tok[0]);
                int r = Integer.parseInt(tok[1]);
                map[r][c] = true;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j]) {
                        Queue<Pos> q = new LinkedList<>();
                        ans++;
                        map[i][j] = false;
                        q.offer(new Pos(i, j));
                        while (!q.isEmpty()) {
                            Pos cur = q.poll();
                            for (int k = 0; k < 4; k++) {
                                int nr = cur.r + dr[k];
                                int nc = cur.c + dc[k];
                                if (nr < 0 || nc < 0 || nr >= N || nc >= M || !map[nr][nc]) continue;
                                map[nr][nc] = false;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
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