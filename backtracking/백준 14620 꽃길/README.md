# 꽃길

> https://www.acmicpc.net/problem/14620

## 문제 설명

- 문제

2017년 4월 5일 식목일을 맞이한 진아는 나무를 심는 대신 하이테크관 앞 화단에 꽃을 심어 등교할 때 마다 꽃길을 걷고 싶었다.

진아가 가진 꽃의 씨앗은 꽃을 심고나면 정확히 1년후에 꽃이 피므로 진아는 다음해 식목일 부터 꽃길을 걸을 수 있다.

하지만 진아에게는 꽃의 씨앗이 세개밖에 없었으므로 세 개의 꽃이 하나도 죽지 않고 1년후에 꽃잎이 만개하길 원한다.

꽃밭은 N*N의 격자 모양이고 진아는 씨앗을 (1,1)~(N,N)의 지점 중 한곳에 심을 수 있다. 꽃의 씨앗은 그림 (a)처럼 심어지며 1년 후 꽃이 피면 그림 (b)모양이 된다.

꽃을 심을 때는 주의할 점이있다. 어떤 씨앗이 꽃이 핀 뒤 다른 꽃잎(혹은 꽃술)과 닿게 될 경우 두 꽃 모두 죽어버린다. 또 화단 밖으로 꽃잎이 나가게 된다면 그 꽃은 죽어버리고 만다.

그림(c)는 세 꽃이 정상적으로 핀 모양이고 그림(d)는 두 꽃이 죽어버린 모양이다.

하이테크 앞 화단의 대여 가격은 격자의 한 점마다 다르기 때문에 진아는 서로 다른 세 씨앗을 모두 꽃이 피게하면서 가장 싼 가격에 화단을 대여하고 싶다.

단 화단을 대여할 때는 꽃잎이 핀 모양을 기준으로 대여를 해야하므로 꽃 하나당 5평의 땅을 대여해야만 한다.

돈이 많지 않은 진아를 위하여 진아가 꽃을 심기 위해 필요한 최소비용을 구해주자!

- 입력

입력의 첫째 줄에 화단의 한 변의 길이 N(6≤N≤10)이 들어온다.

이후 N개의 줄에 N개씩 화단의 지점당 가격(0≤G≤200)이 주어진다.

- 출력

꽃을 심기 위한 최소 비용을 출력한다.

## 접근 방법

전체 화단에서 꽃의 중심을 기준으로 3개의 좌표를 조합으로 뽑아야 한다.  
모든 좌표를 루프하며 가능한 3개의 좌표를 뽑은 뒤, 각 해당 좌표에서 상하좌우 범위에 걸쳐 방문을 할 때 총 15개의 좌표가 서로 겹치지 않을 경우 모두 꽃이 피는 경우.  
이 때의 비용을 이전의 비용과 비교하며 최소비용으로 갱신해주면 된다.  
최대 좌표 개수가 100, 조합의 최댓값이 100C3이므로 완전 탐색으로 풀 수 있다.

## 문제 해결 과정

- 첫 번째 시도:
    - (1, 1) 부터 조합을 뽑는 함수 `comb()`를 재귀하며 가능한 조합에 대해 모든 좌표를 탐색.
    - 한편 이중 for문에서 열 범위를 설정할 때, `for (int j = c; j < n; j++)`로 설정하여 행이 넘어갔을 때, `c`값 보다 작은 값을 탐색하지 못하게 설정하여 실패.
- 최종 해결 방법:
    - 각 좌표의 비용 최댓값은 200이므로 총 비용의 최댓값 `ans = 3000`으로 초기화.
    - `int[][] tmp`에 재귀하며 얻은 세 좌표의 조합 저장.
    - 조합을 저장하는 함수 `comb()`를 재귀하며 `cnt == 3`이 되는 경우 해당 조합으로 꽃이 피는지 검사하며 비용 갱신 `calc()`.
        - 조합을 완성하면, 만약 15개의 좌표가 서로 겹치지 않는 경우에 한해 `ans = Math.min(ans, ret)`
        - 조합을 완성하지 않았다면, 좌표를 이동하며 조합 저장. 재귀.
- 다른 해결 방법:
    - 보다 직관적으로 조합을 구하는 것이 아닌, 새로운 꽃을 심을 수 있을 때만 심는 완전 탐색으로 풀었다.
    - (0, 0)부터 모든 좌표를 탐색하며, 해당 좌표가 직전 심은 꽃 상황 내에서 꽃을 심을 수 있는지 `check()`로 확인.
        - 가능하다면 `setFlower()`로 해당 좌표를 기준으로 방문 처리하며 꽃 심기.
        - 다음 좌표로 재귀하며, 재귀가 끝나면 `eraseFlower()`로 방문 처리 원상 복구.
- 25.8.16. 다시 푼 방법:
    - `N`이 최대 10이고 3개의 위치에 꽃을 심어야 하므로, 탐색의 최댓값은 100C3 = 대략 몇 십만 대.
    - 따라서 브루트 포스로 가능한 모든 3개의 조합을 찾는 방식 결정.
        - DFS 탐색을 통해 모든 좌표마다 해당 좌표에 꽃을 심을 수 있는지 `isValid(r, c)` 검사.
            - 해당 좌표를 포함해 동서남북 다섯 좌표가 전부 방문 안했으면 가능.
        - 꽃을 심을 수 있으면, `flower(r, c)`로 꽃을 심으며 방문 처리 + 심는 비용 리턴.
        - 이후 `dfs(dep + 1, i + 1, sum + tmp)`를 통해 해당 좌표 방문 처리 후 다음 DFS 탐색.
        - 탐색이 끝나면 다시 방문 처리 취소 `unFlower(r, c)`로 다섯 좌표 방문 처리 취소.
    - 조합을 구하는 DFS 탐색에 있어서, `0`부터 `N * N`을 최대로, 각 좌표를 하나의 인덱스로 나타내었다.
        - 해당 인덱스는 `N`으로 나눈 값이 행, `N`으로 나머지 연산을 한 값이 열이 된다.

## 다른 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int n, ans;
    static int[][] map;
    static boolean[][] vis;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = 3000;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] tok = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tok[j]);
            }
        }
        vis = new boolean[n][n];

        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int cnt, int sum) {
        if (cnt == 3) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check(i, j)) {
                    int tmp = setFlower(i, j);
                    dfs(cnt + 1, sum + tmp);
                    eraseFlower(i, j);
                }
            }
        }
    }

    static boolean check(int r, int c) {
        if (vis[r][c]) return false;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n || vis[nr][nc]) return false;
        }
        return true;
    }

    static int setFlower(int r, int c) {
        int ret = 0;
        vis[r][c] = true;
        ret += map[r][c];
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            vis[nr][nc] = true;
            ret += map[nr][nc];
        }
        return ret;
    }

    static void eraseFlower(int r, int c) {
        vis[r][c] = false;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            vis[nr][nc] = false;
        }
    }
}
```

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N, ans = Integer.MAX_VALUE;
    static int[][] board;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    static boolean[][] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        vis = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] tok = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(tok[j]);
            }
        }

        dfs(0, 0, 0);

        System.out.println(ans);
    }

    static void dfs(int dep, int idx, int sum) {
        if (dep == 3) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = idx; i < N * N; i++) {
            int r = i / N;
            int c = i % N;
            if (isValid(r, c)) {
                int tmp = flower(r, c);
                dfs(dep + 1, i + 1, sum + tmp);
                unFlower(r, c);
            }
        }
    }

    static boolean isValid(int r, int c) {
        if (vis[r][c]) return false;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N || vis[nr][nc]) return false;
        }
        return true;
    }

    static int flower(int r, int c) {
        int ret = board[r][c];
        vis[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            ret += board[nr][nc];
            vis[nr][nc] = true;
        }
        return ret;
    }

    static void unFlower(int r, int c) {
        vis[r][c] = false;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            vis[nr][nc] = false;
        }
    }
}
```