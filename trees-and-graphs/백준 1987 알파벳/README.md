# 알파벳

> https://www.acmicpc.net/problem/1987

## 문제 설명

- 문제

세로
$R$칸, 가로
$C$칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (
$1$행
$1$열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수
없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

- 입력

첫째 줄에
$R$과
$C$가 빈칸을 사이에 두고 주어진다. (
$1 ≤ R,C ≤ 20$) 둘째 줄부터
$R$개의 줄에 걸쳐서 보드에 적혀 있는
$C$개의 대문자 알파벳들이 빈칸 없이 주어진다.

- 출력

첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

## 접근 방법

동서남북으로 이동하며 좌측 상단에서 최대로 이동할 수 있는 거리를 구하는 문제이므로 DFS 탐색 사용.  
DFS의 depth가 가장 많이 갈 수 있는 경로가 정답.  
한편 한번 지난 좌표 뿐만 아니라 알파벳도 검증해야 하므로 셋을 활용해 검증.

## 문제 해결 과정

- 최종 해결 방법:
    - `(0, 0)` 좌표부터 방문 처리하며 `dfs()` 메서드 재귀.
        - 재귀할 때마다 최대의 칸 수 `int ans`를 최댓값 갱신.
            - 한편 다음 노드를 갈 수 있는지 판별할 때, 만약 갈 수 있어서 재귀를 할 수 있다면 방문 처리를 원상 복구하는 것이 핵심.
            - 또한 단순 `vis` 배열의 방문 처리 원상 복구 뿐만 아니라, `Set<Character> set` 또한 해당 알파벳을 지워줘야 한다.
- 25.8.12. 다시 푼 방법:
    - 지금까지 지나지 않은 알파벳만 이동할 수 있다는 뜻은, 곧 지난 알파벳들이 방문처리와 동일하다는 뜻이다.
    - 따라서 `HashSet<Character> set`에 지금까지 지난 알파벳들을 저장하며, 다음 노드의 알파벳이 셋에 있는지 없는지만 검사하며 진행.
        - 또한 `dfs()`가 시작될 때마다 셋의 크기를 체크하며 최대길이 갱신.

## 다시 푼 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution {

    static int R, C, ans = 0;
    static char[][] board;
    static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tok = br.readLine().split(" ");
        R = Integer.parseInt(tok[0]);
        C = Integer.parseInt(tok[1]);
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        HashSet<Character> set = new HashSet<>();
        set.add(board[0][0]);
        dfs(0, 0, set);

        System.out.println(ans);
    }

    static void dfs(int r, int c, HashSet<Character> set) {
        ans = Math.max(ans, set.size());
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr == R || nc == C || set.contains(board[nr][nc])) continue;
            set.add(board[nr][nc]);
            dfs(nr, nc, set);
            set.remove(board[nr][nc]);
        }
    }
}
```
