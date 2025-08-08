# 치즈

> https://www.acmicpc.net/problem/2636

## 문제 설명

- 문제

아래 <그림 1>과 같이 정사각형 칸들로 이루어진 사각형 모양의 판이 있고, 그 위에 얇은 치즈(회색으로 표시된 부분)가 놓여 있다. 판의 가장자리(<그림 1>에서 네모 칸에 X친 부분)에는 치즈가 놓여 있지 않으며
치즈에는 하나 이상의 구멍이 있을 수 있다.

이 치즈를 공기 중에 놓으면 녹게 되는데 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다. 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다. <그림
1>의 경우, 치즈의 구멍을 둘러싼 치즈는 녹지 않고 ‘c’로 표시된 부분만 한 시간 후에 녹아 없어져서 <그림 2>와 같이 된다.

<그림 1> 원래 치즈 모양

다시 한 시간 후에는 <그림 2>에서 ‘c’로 표시된 부분이 녹아 없어져서 <그림 3>과 같이 된다.

<그림 2> 한 시간 후의 치즈 모양

<그림 3> 두 시간 후의 치즈 모양

<그림 3>은 원래 치즈의 두 시간 후 모양을 나타내고 있으며, 남은 조각들은 한 시간이 더 지나면 모두 녹아 없어진다. 그러므로 처음 치즈가 모두 녹아 없어지는 데는 세 시간이 걸린다. <그림 3>과 같이 치즈가
녹는 과정에서 여러 조각으로 나누어 질 수도 있다.

입력으로 사각형 모양의 판의 크기와 한 조각의 치즈가 판 위에 주어졌을 때, 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 구하는
프로그램을 작성하시오.

- 입력

첫째 줄에는 사각형 모양 판의 세로와 가로의 길이가 양의 정수로 주어진다. 세로와 가로의 길이는 최대 100이다. 판의 각 가로줄의 모양이 윗 줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다. 치즈가 없는 칸은
0, 치즈가 있는 칸은 1로 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.

- 출력

첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고, 둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.

## 접근 방법

치즈를 둘러싸고 있는 공기를 하나의 연결된 컴포넌트로 본다.  
공기와 맞닿은 치즈의 가장자리만 BFS 탐색을 활용해 녹이면서 방문 처리.  
이 과정에서 남은 치즈의 조각 개수와 걸린 시간을 저장하며, 모두 녹으면 출력.

## 문제 해결 과정

- 첫 번째 시도:
    - `0`의 값을 지닌 모든 공기에서 걸린 시간마다 동서남북 방향으로 공기 영역 확장하는 방법 사용.
        - 생각해보니 아직 바깥으로 치즈가 둘러있는 내부의 공기 영역은 처음부터 확장할 수 없음.
        - 치즈의 가장 바깥쪽만을 녹이는 방법이 필요하다고 생각.
- 최종 해결 방법:
    - `int[n][m] map`에 최초 치즈 상태를 입력받으며, 치즈 조각의 최댓값 `cnt` 카운팅.
        - 갈수록 치즈 조각은 녹아 없어지므로 입력받는 상태가 가장 많을 때. `cnt == 0`이 될 때까지 단계 `lev` 반복 증가.
    - `map`의 가장 바깥쪽 가장자리는 항상 공기 영역이므로 `(0,0)`부터 동서남북으로 이동하며 방문 처리.
        - 방문하지 않은 지역만 방문하되, 만약 `0`의 값을 만나면 다시 큐에 삽입.
        - `1`의 값을 만나면 현재 전체 치즈의 가장자리이므로 값을 `0`으로 바꿔 녹인 뒤, 치즈 조각 감소.
    - BFS를 더이상 진행할 수 없을 때, 남은 치즈가 없다면 종료. 남아있다면 `pre`에 현재 `cnt`를 갱신하고 다시 반복.
- 다른 해결 방법:
    - DFS 탐색을 활용해 치즈 가장자리를 `0`으로 바꿔 보았다.
    - `ArrayList<Pos> list`에 `1`의 값을 가진 가장자리 좌표를 모두 삽입.
        - 이 DFS가 끝났을 때, 리스트의 크기가 곧 해당 `lev`에서 `0`으로 바꾼 `cnt` 값과 동일.
    - 이후 전체를 탐색하며 치즈 조각이 남았는지 확인 및 반복.
- 25.8.8. 다시 푼 방법:
    - 외부 공기 좌표들을 BFS 탐색을 통해 리스트에 넣고, 모든 치즈를 하나씩 탐색하며 해당 치즈가 외부 공기와 맞닿아있는지 검사.
    - 만약 닿아있다면 `stk1`에, 닿아있지 않으면 `stk2`에 따로 넣는다.
        - `stk1`은 녹아 없어지므로 모두 `0`으로 값을 바꾸고, `stk2`는 내부 치즈이므로 다시 치즈 큐에 삽입.
    - 이 과정을 반복하며 `que`의 사이즈가 0이 되면 종료.
    - 한편 기존에 푼 방법에 비해 시간복잡도가 크게 증가했는데, 한 번의 루프마다 두 번의 BFS 탐색을 진행하기 때문.
        - 개선하려면 한번의 외부공기 탐색에서 맞닿은 치즈를 리스트에 추가하고, 추가한 리스트 값들을 0으로 바꿔주면서 치즈를 녹여주면 BFS 탐색 한번이 가능.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {

    static int n, m, cnt, lev;
    static int[][] map;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static ArrayList<Pos> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] tok = br.readLine().split(" ");
        n = Integer.parseInt(tok[0]);
        m = Integer.parseInt(tok[1]);
        map = new int[n][m];
        cnt = 0;
        for (int i = 0; i < n; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
            }
        }
        lev = 0;

        while (true) {
            lev++;
            vis = new boolean[n][m];
            list = new ArrayList<>();

            dfs(0, 0);
            cnt = list.size();
            for (Pos pos : list) {
                map[pos.r][pos.c] = 0;
            }

            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) flag = true;
                }
            }
            if (!flag) break;
        }
        sb.append(lev).append("\n").append(cnt);
        System.out.println(sb);
    }

    static void dfs(int r, int c) {
        vis[r][c] = true;
        if (map[r][c] == 1) {
            list.add(new Pos(r, c));
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || vis[nr][nc]) continue;
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

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    static int r, c, pre = 0, cnt = 0;
    static int[][] board;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        r = Integer.parseInt(tok[0]);
        c = Integer.parseInt(tok[1]);
        board = new int[r][c];
        Queue<Pos> que = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            tok = br.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(tok[j]);
                if (board[i][j] == 1) que.add(new Pos(i, j));
            }
        }

        Stack<Pos> stk1 = new Stack<>();
        Stack<Pos> stk2 = new Stack<>();
        pre = que.size();
        while (!que.isEmpty()) {
            cnt++;
            ArrayList<Pos> airList = airList();

            while (!que.isEmpty()) {
                Pos cur = que.poll();
                boolean flag = false;
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (nr < 0 || nc < 0 || nr == r || nc == c) continue;
                    for (Pos air : airList) {
                        if (air.r == nr && air.c == nc) {
                            flag = true;
                            break;
                        }
                    }
                }

                if (flag) stk1.add(cur);
                else stk2.add(cur);
            }

            while (!stk1.isEmpty()) {
                Pos cur = stk1.pop();
                board[cur.r][cur.c] = 0;
            }
            while (!stk2.isEmpty()) {
                Pos cur = stk2.pop();
                que.add(cur);
            }

            if (que.isEmpty()) break;
            pre = que.size();
        }

        System.out.println(cnt);
        System.out.println(pre);
    }

    static ArrayList<Pos> airList() {
        ArrayList<Pos> ret = new ArrayList<>();
        Queue<Pos> que = new LinkedList<>();
        boolean[][] vis = new boolean[r][c];
        que.add(new Pos(0, 0));
        vis[0][0] = true;
        ret.add(new Pos(0, 0));
        while (!que.isEmpty()) {
            Pos cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nc < 0 || nr == r || nc == c || vis[nr][nc] || board[nr][nc] == 1) continue;
                vis[nr][nc] = true;
                que.add(new Pos(nr, nc));
                ret.add(new Pos(nr, nc));
            }
        }

        return ret;
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