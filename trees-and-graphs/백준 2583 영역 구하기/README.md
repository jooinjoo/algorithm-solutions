# 영역 구하기

> https://www.acmicpc.net/problem/2583

## 문제 설명

- 문제

눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다. 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.

예를 들어 M=5, N=7 인 모눈종이 위에 <그림 1>과 같이 직사각형 3개를 그렸다면, 그 나머지 영역은 <그림 2>와 같이 3개의 분리된 영역으로 나누어지게 된다.

<그림 2>와 같이 분리된 세 영역의 넓이는 각각 1, 7, 13이 된다.

M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는
프로그램을 작성하시오.

- 입력

첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다. 둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽
위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다. 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다. 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는
경우는 없다.

- 출력

첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.

## 접근 방법

`int[m][n] map`에 모눈종이 입력.  
(x1, y1) ~ (x2, y2)에 직사각형을 `1`로 입력. 이 때, (x2, y2)는 각각 1씩 빼주고 입력.  
`map`을 루프하면서 방문하지 않은 경우, BFS를 활용해 connected component 크기 탐색.

## 문제 해결 과정

- 최종 해결 방법:
    - 모눈종이에 직사각형을 입력할 때, 두 번째 (x2, y2) 입력을 주의하는 것이 핵심.
        - 우측 상단의 직사각형 좌표이므로, 범위 초과가 나지 않도록 그 직전까지 채워주는 것이 중요함.
        - 정답 도출에는 상관없지만, (x, y)를 (r, c) 형태로 치환하여 채워지는 모눈종이는 상하 반전된 모습.
    - BFS를 활용하여, 방문하지 않은 모눈종이일 때 연결된 모든 범위를 탐색한 뒤, 그 넓이를 `list`에 추가.
    - 시간 복잡도는 O(m * n * k) 인데 모두 100 이하이므로 쉽게 통과.
- 다른 해결 방법:
    - connected component이므로 DFS를 활용해서도 풀어보았다.
    - 크게 달라진 것은 없고, `cnt`를 따로 카운팅하기 보다는 `list.size()`로 해결.
- 25.1.27. 다시 푼 방법:
    - 모눈종이의 상하를 반전하여, 해당하는 직사각형들만큼 방문처리.
    - 이후 루프하며 방문하지 않은 컴포넌트의 개수를 카운팅하며, bfs를 통해 각 컴포넌트의 넓이 구하기.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    static int m, n, k;
    static int[][] map;
    static ArrayList<Integer> list;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] words = br.readLine().split(" ");
        m = Integer.parseInt(words[0]);
        n = Integer.parseInt(words[1]);
        k = Integer.parseInt(words[2]);
        map = new int[m][n];
        for (int i = 0; i < k; i++) {
            words = br.readLine().split(" ");
            int x1 = Integer.parseInt(words[0]);
            int y1 = Integer.parseInt(words[1]);
            int x2 = Integer.parseInt(words[2]);
            int y2 = Integer.parseInt(words[3]);
            for (int j = y1; j < y2; j++) {
                for (int k = x1; k < x2; k++) {
                    map[j][k] = 1;
                }
            }
        }
        list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    list.add(dfs(i, j));
                }
            }
        }

        Collections.sort(list);
        sb.append(list.size()).append("\n");
        for (int i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static int dfs(int r, int c) {
        map[r][c] = 1;
        int ret = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || map[nr][nc] == 1) continue;
            ret += dfs(nr, nc);
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
import java.util.ArrayList;
import java.util.Collections;
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
        String[] tok = br.readLine().split(" ");
        M = Integer.parseInt(tok[0]);
        N = Integer.parseInt(tok[1]);
        K = Integer.parseInt(tok[2]);
        map = new boolean[M][N];
        while (K-- > 0) {
            tok = br.readLine().split(" ");
            int c1 = Integer.parseInt(tok[0]);
            int r1 = Integer.parseInt(tok[1]);
            int c2 = Integer.parseInt(tok[2]) - 1;
            int r2 = Integer.parseInt(tok[3]) - 1;
            for (int i = r1; i <= r2; i++) {
                for (int j = c1; j <= c2; j++) {
                    map[i][j] = true;
                }
            }
        }

        ArrayList<Integer> vals = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j]) {
                    ans++;
                    vals.add(bfs(i, j));
                }
            }
        }
        Collections.sort(vals);
        sb.append(ans).append("\n");
        for (int v : vals) {
            sb.append(v).append(" ");
        }
        System.out.println(sb.toString());
    }

    static int bfs(int r, int c) {
        int cnt = 0;
        Queue<Pos> q = new LinkedList<>();
        map[r][c] = true;
        q.offer(new Pos(r, c));
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= M || nc >= N || map[nr][nc]) continue;
                map[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }

        return cnt;
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